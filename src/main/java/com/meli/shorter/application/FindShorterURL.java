package com.meli.shorter.application;

import com.meli.shorter.domain.services.ShorterURLPort;
import com.meli.shorter.infra.primary.mapper.DataURLMapper;
import com.meli.shorter.infra.secondary.cache.RedisCache;
import com.meli.shorter.infra.secondary.dto.DataURL;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public final class FindShorterURL {

    private final ShorterURLPort shorterURLPort;
    private final RedisCache redisCache;
    private final DataURLMapper dataURLMapper;

    public Mono<DataURL> findByShorter(String uri) {
        return redisCache.findByShorter(uri)
                .flatMap(e -> Mono.just(dataURLMapper.toData(e)))
                .switchIfEmpty(shorterURLPort.find(uri));
    }

    public Mono<DataURL> findById(String uri) {
        return redisCache.findById(uri)
                .flatMap(e -> Mono.just(dataURLMapper.toData(e)))
                .switchIfEmpty(shorterURLPort.find(uri));
    }

}
