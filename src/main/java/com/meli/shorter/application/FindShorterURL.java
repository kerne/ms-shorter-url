package com.meli.shorter.application;

import com.meli.shorter.domain.services.ShorterURLCachePort;
import com.meli.shorter.domain.services.ShorterURLPersistencePort;
import com.meli.shorter.infra.secondary.dto.DataURL;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FindShorterURL {

    private final ShorterURLPersistencePort shorterURLPersistencePort;
    private final ShorterURLCachePort shorterURLCachePort;

    public Mono<DataURL> findByShorterURL(String uri) {
        return shorterURLCachePort.findByShorterURL(uri)
                .switchIfEmpty(shorterURLPersistencePort.find(uri));
    }

    public Mono<DataURL> findByURL(String uri) {
        return shorterURLCachePort.findByFullURL(uri)
                .switchIfEmpty(shorterURLPersistencePort.find(uri));
    }

}
