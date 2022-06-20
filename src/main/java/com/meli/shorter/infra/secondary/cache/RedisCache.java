package com.meli.shorter.infra.secondary.cache;

import com.meli.shorter.infra.secondary.dto.DataRedis;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public final class RedisCache {

    private final ReactiveRedisOperations<String, DataRedis> reactiveRedisOperations;


    public Mono<DataRedis> findById(String id) {
        return this.reactiveRedisOperations.opsForValue().get(id);
    }

    public Mono<DataRedis> findByShorter(String shorter) {
        return this.reactiveRedisOperations.keys("*")
                .flatMap(reactiveRedisOperations.opsForValue()::get)
                .filter(e -> e.getUrlShorter().equalsIgnoreCase(shorter))
                .last();
    }

    public Mono<Void> save(DataRedis dataRedis) {
        return this.reactiveRedisOperations.opsForValue().set(dataRedis.getUrlFull(), dataRedis).then();
    }

    public Mono<Boolean> deleteById(String key) {
        return this.reactiveRedisOperations.opsForValue().delete(key);
    }
}
