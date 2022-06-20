package com.meli.shorter.infra.secondary.persistence;

import com.meli.shorter.domain.services.ShorterURLPort;
import com.meli.shorter.infra.primary.mapper.DataURLMapper;
import com.meli.shorter.infra.secondary.cache.RedisCache;
import com.meli.shorter.infra.secondary.dto.DataURL;
import com.meli.shorter.infra.secondary.repository.ShorterURLRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ShorterURLPersistence implements ShorterURLPort {

    private final ShorterURLRepository shorterURLRepository;
    private final RedisCache redisCache;
    private final DataURLMapper dataURLMapper;

    @Override
    public Mono<DataURL> save(DataURL shorterURL) {
        return shorterURLRepository.save(shorterURL);
    }

    @Override
    public Mono<DataURL> find(String shorter) {
        return shorterURLRepository.findDataURLByUrlShorter(shorter);
    }

    @Override
    public Mono<Void> remove(String key) {
        return find(key)
                .flatMap(shorterURLRepository::delete);
    }
}
