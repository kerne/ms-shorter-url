package com.meli.shorter.domain.services;

import com.meli.shorter.infra.secondary.dto.DataURL;
import reactor.core.publisher.Mono;

public interface ShorterURLCachePort {

    Mono<DataURL> findByFullURL(String url);

    Mono<DataURL> findByShorterURL(String urlShort);

    Mono<DataURL> save(DataURL dataRedis);

    Mono<Boolean> deleteById(String url);
}
