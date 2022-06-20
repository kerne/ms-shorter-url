package com.meli.shorter.domain.services;

import com.meli.shorter.infra.secondary.dto.DataURL;
import reactor.core.publisher.Mono;

public interface ShorterURLPort {

    Mono<DataURL> save(DataURL dataURL);

    Mono<DataURL> find(String id);

    Mono<Void> remove(String key);

}
