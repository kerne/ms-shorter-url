package com.meli.shorter.infra.config;

import com.meli.shorter.infra.secondary.dto.DataRedis;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    ReactiveRedisOperations<String, DataRedis> redisOperations(ReactiveRedisConnectionFactory factory) {
        Jackson2JsonRedisSerializer<DataRedis> serializer = new Jackson2JsonRedisSerializer<>(DataRedis.class);

        RedisSerializationContext.RedisSerializationContextBuilder<String, DataRedis> builder =
                RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

        RedisSerializationContext<String, DataRedis> context = builder.value(serializer).build();

        return new ReactiveRedisTemplate<>(factory, context);
    }

}
