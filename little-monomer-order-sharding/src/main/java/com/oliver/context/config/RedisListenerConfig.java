package com.oliver.context.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @author xiaorui
 */
@Configuration
public class RedisListenerConfig {
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory redisConnectionFactory) {
        // Redis message listener
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        // Set up Redis Link Factory
        container.setConnectionFactory(redisConnectionFactory);
        return container;
    }
}
