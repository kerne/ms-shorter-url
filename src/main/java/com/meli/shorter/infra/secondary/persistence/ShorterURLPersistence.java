package com.meli.shorter.infra.secondary.persistence;

import com.meli.shorter.domain.URLProduct;
import com.meli.shorter.domain.services.ShorterURLPersistencePort;
import com.meli.shorter.infra.secondary.dto.DataURL;
import com.meli.shorter.infra.secondary.repository.ShorterURLRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ShorterURLPersistence implements ShorterURLPersistencePort {

    private final ShorterURLRepository shorterURLRepository;

    @Override
    public Mono<DataURL> save(URLProduct shorterURL) {
        var dataURL = new DataURL(shorterURL.id(),
                shorterURL.original().value(),
                shorterURL.shorter().value());
        return shorterURLRepository.save(dataURL);
    }

    @Override
    public Mono<DataURL> find(String shorter) {
        return shorterURLRepository.findDataURLByUrlShorter(shorter)
                .switchIfEmpty(Mono.error(new Exception("Not Found Resource")));
    }

    @Override
    public Mono<Void> remove(String key) {
        return find(key).flatMap(shorterURLRepository::delete);
    }
}
