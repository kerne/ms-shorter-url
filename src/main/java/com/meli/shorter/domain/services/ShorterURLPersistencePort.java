package com.meli.shorter.domain.services;

import com.meli.shorter.domain.URLProduct;
import com.meli.shorter.infra.secondary.dto.DataURL;
import reactor.core.publisher.Mono;

public interface ShorterURLPersistencePort {

    Mono<DataURL> save(URLProduct urlProduct);

    Mono<DataURL> find(String id);

    Mono<Void> remove(String key);

}
