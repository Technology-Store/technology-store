package com.winnguyen1905.technologystore.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfiguration {
    
    // @Bean
    // RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory connectionFactory) {
    //     RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();
    //     redisTemplate.setEnableTransactionSupport(true);
    //     return redisTemplate;

    // }

}