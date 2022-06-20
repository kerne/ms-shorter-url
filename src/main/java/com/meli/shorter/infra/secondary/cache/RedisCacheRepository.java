package com.meli.shorter.infra.secondary.cache;

import com.meli.shorter.domain.services.ShorterURLCachePort;
import com.meli.shorter.infra.primary.mapper.DataURLMapper;
import com.meli.shorter.infra.secondary.dto.DataRedis;
import com.meli.shorter.infra.secondary.dto.DataURL;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public final class RedisCacheRepository implements ShorterURLCachePort {

    private final ReactiveRedisOperations<String, DataRedis> reactiveRedisOperations;
    private final DataURLMapper dataURLMapper;


    public Mono<DataURL> findByFullURL(String url) {
        return this.reactiveRedisOperations.opsForValue().get(url)
                .flatMap(e -> Mono.just(dataURLMapper.toData(e)));
    }

    public Mono<DataURL> findByShorterURL(String urlShort) {
        return this.reactiveRedisOperations.keys("*")
                .flatMap(reactiveRedisOperations.opsForValue()::get)
                .filter(e -> e.getUrlShorter().equalsIgnoreCase(urlShort))
                .last()
                .flatMap(e -> Mono.just(dataURLMapper.toData(e)));
    }

    public Mono<DataURL> save(DataURL dataRedis) {
        return this.reactiveRedisOperations
                .opsForValue()
                .set(dataRedis.getUrlFull(), dataURLMapper.toRedis(dataRedis))
                .then(Mono.just(dataRedis));
    }

    public Mono<Boolean> deleteById(String url) {
        return this.reactiveRedisOperations.opsForValue().delete(url);
    }
}
