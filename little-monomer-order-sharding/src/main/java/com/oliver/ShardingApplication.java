package com.oliver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author xiaorui
 */
@SpringBootApplication
@EnableCaching
@EnableScheduling
@MapperScan(basePackages = "com.oliver.mapper")
public class ShardingApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShardingApplication.class, args);
    }

}
