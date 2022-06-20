package com.meli.shorter.infra.config;

import com.meli.shorter.application.CreateShorterURL;
import com.meli.shorter.application.FindShorterURL;
import com.meli.shorter.application.RemoveShorterURL;
import com.meli.shorter.infra.secondary.cache.RedisCacheRepository;
import com.meli.shorter.infra.secondary.persistence.ShorterURLPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreateShorterURL createShorterURL(ShorterURLPersistence shorterURLPersistence, RedisCacheRepository redisCacheRepository) {
        return new CreateShorterURL(shorterURLPersistence, redisCacheRepository);
    }

    @Bean
    public FindShorterURL findShorterURL(ShorterURLPersistence shorterURLPersistence, RedisCacheRepository redisCacheRepository) {
        return new FindShorterURL(shorterURLPersistence, redisCacheRepository);
    }

    @Bean
    public RemoveShorterURL deleteShorterURL(ShorterURLPersistence shorterURLPersistence, RedisCacheRepository redisCacheRepository, FindShorterURL findShorterURL) {
        return new RemoveShorterURL(shorterURLPersistence, redisCacheRepository, findShorterURL);
    }

}
