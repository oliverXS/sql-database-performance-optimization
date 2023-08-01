package com.oliver.context.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiaorui
 * Multi-data source configuration
 */
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceConfig {
    /**
     * 主库数据源信息
     */
    private Map<String,String> masters;
    /**
     * 从库数据源信息
     */
    private List<Map<String,String>> slaves;

    @Bean
    public DataSource masterDataSource() throws Exception {;
        if (CollectionUtils.isEmpty(masters)) {
            throw new Exception("主库数据源不能为空");
        }
        return DruidDataSourceFactory.createDataSource(masters);
    }

    @Bean
    public List<DataSource> slaveDataSources() throws Exception {
        if (CollectionUtils.isEmpty(slaves)) {
            throw new Exception("从库数据源不能为空");
        }
        final List<DataSource> dataSources = new ArrayList<>();
        for (Map map : slaves) {
            dataSources.add(DruidDataSourceFactory.createDataSource(map));
        }
        return dataSources;
    }

    @Bean
    @Primary
    @DependsOn({"masterDataSource", "slaveDataSources"})
    public DataSourceRouter routingDataSource() throws Exception {
        final Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceContextHolder.MASTER, masterDataSource());
        for (int i = 0; i < slaveDataSources().size(); i++) {
            targetDataSources.put(DataSourceContextHolder.SLAVE + i, slaveDataSources().get(i));
        }
        final DataSourceRouter routingDataSource = new DataSourceRouter();
        routingDataSource.setTargetDataSources(targetDataSources);
        routingDataSource.setDefaultTargetDataSource(masterDataSource());
        return routingDataSource;
    }


    /**
     * To set up a transaction, the transaction needs to know which data source is currently used to process the transaction.
     *
     * @return
     */
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager() throws Exception {
        return new DataSourceTransactionManager(routingDataSource());
    }

    public Map<String, String> getMasters() {
        return masters;
    }

    public void setMasters(Map<String, String> masters) {
        this.masters = masters;
    }

    public List<Map<String, String>> getSlaves() {
        return slaves;
    }

    public void setSlaves(List<Map<String, String>> slaves) {
        this.slaves = slaves;
    }
}
