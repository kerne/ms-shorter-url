package com.meli.shorter.infra.secondary.repository;

import com.meli.shorter.infra.secondary.dto.DataURL;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ShorterURLRepository extends ReactiveCrudRepository<DataURL, String> {

    Mono<DataURL> findDataURLByUrlShorter(String urlShorter);

}
