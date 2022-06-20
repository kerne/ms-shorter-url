package com.meli.shorter.application;

import com.meli.shorter.domain.services.ShorterURLCachePort;
import com.meli.shorter.domain.services.ShorterURLPersistencePort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public final class RemoveShorterURL {

    private final ShorterURLPersistencePort shorterURLPersistencePort;
    private final ShorterURLCachePort shorterURLCachePort;
    private final FindShorterURL findShorterURL;

    public Mono<Void> remove(String shorterUrl) {
        return findShorterURL.findByShorterURL(shorterUrl)
                .flatMap(e -> shorterURLCachePort.deleteById(e.getUrlFull()))
                .then(shorterURLPersistencePort.remove(shorterUrl));
    }

}
