package com.meli.shorter.application;

import com.meli.shorter.domain.OriginalURL;
import com.meli.shorter.domain.ShorterURL;
import com.meli.shorter.domain.URLProduct;
import com.meli.shorter.domain.services.ShorterURLCachePort;
import com.meli.shorter.domain.services.ShorterURLPersistencePort;
import com.meli.shorter.infra.secondary.dto.DataURL;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public final class CreateShorterURL {

    private final ShorterURLPersistencePort shorterURLPersistencePort;
    private final ShorterURLCachePort shorterURLCachePort;

    public Mono<DataURL> create(String url) {
        var shorterUrl = URLProduct.create(OriginalURL.create(url), ShorterURL.create());
        return shorterURLPersistencePort.save(shorterUrl).flatMap(shorterURLCachePort::save);
    }

}
