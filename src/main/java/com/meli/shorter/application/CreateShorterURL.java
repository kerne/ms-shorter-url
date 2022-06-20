package com.meli.shorter.application;

import com.meli.shorter.domain.ShorterURL;
import com.meli.shorter.domain.services.ShorterURLPort;
import com.meli.shorter.infra.primary.mapper.DataURLMapper;
import com.meli.shorter.infra.secondary.cache.RedisCache;
import com.meli.shorter.infra.secondary.dto.DataRedis;
import com.meli.shorter.infra.secondary.dto.DataURL;
import com.meli.shorter.infra.utils.URLUtils;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public final class CreateShorterURL {

    private final ShorterURLPort shorterURLPort;
    private final RedisCache redisCache;
    private final DataURLMapper dataURLMapper;

    public Mono<DataURL> create(String url) {
        var shorterUrl = URLUtils.urlShorter(url);
        return Mono.just(dataURLMapper.toRedis(shorterUrl))
                .flatMap(redisCache::save)
                .flatMap(aBoolean -> shorterURLPort.save(shorterUrl));
    }

}
