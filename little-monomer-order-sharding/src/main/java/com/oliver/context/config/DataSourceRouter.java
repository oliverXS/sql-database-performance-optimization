package com.oliver.context.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author xiaorui
 */
@Slf4j
public class DataSourceRouter extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        log.info("Current data source is:"+DataSourceContextHolder.getDataSourceType());
        // Return to the selected data source
        return DataSourceContextHolder.getDataSourceType();    }
}
