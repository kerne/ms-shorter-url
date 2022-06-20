package com.meli.shorter.infra.config;

import com.meli.shorter.application.CreateShorterURL;
import com.meli.shorter.application.RemoveShorterURL;
import com.meli.shorter.application.FindShorterURL;
import com.meli.shorter.infra.primary.mapper.DataURLMapper;
import com.meli.shorter.infra.secondary.cache.RedisCache;
import com.meli.shorter.infra.secondary.persistence.ShorterURLPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreateShorterURL createShorterURL(ShorterURLPersistence shorterURLPersistence, RedisCache redisCache,DataURLMapper dataURLMapper) {
        return new CreateShorterURL(shorterURLPersistence, redisCache,dataURLMapper);
    }

    @Bean
    public FindShorterURL findShorterURL(ShorterURLPersistence shorterURLPersistence, RedisCache redisCache, DataURLMapper dataURLMapper) {
        return new FindShorterURL(shorterURLPersistence, redisCache, dataURLMapper);
    }

    @Bean
    public RemoveShorterURL deleteShorterURL(ShorterURLPersistence shorterURLPersistence, RedisCache redisCache, FindShorterURL findShorterURL) {
        return new RemoveShorterURL(shorterURLPersistence, redisCache, findShorterURL);
    }

}
