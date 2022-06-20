package com.meli.shorter.application;

import com.meli.shorter.domain.services.ShorterURLPort;
import com.meli.shorter.infra.secondary.cache.RedisCache;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public final class RemoveShorterURL {

    private final ShorterURLPort shorterURLPort;
    private final RedisCache redisCache;
    private final FindShorterURL findShorterURL;

    public Mono<Void> delete(String shorterUrl) {
        return findShorterURL.findByShorter(shorterUrl)
                .flatMap(e -> redisCache.deleteById(e.getUrlFull()))
                .then(shorterURLPort.remove(shorterUrl));
    }

}
