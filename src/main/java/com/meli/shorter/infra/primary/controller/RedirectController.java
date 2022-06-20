package com.meli.shorter.infra.primary.controller;

import com.meli.shorter.infra.secondary.cache.RedisCache;
import lombok.RequiredArgsConstructor;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.http.HttpStatus.TEMPORARY_REDIRECT;

@Controller
@RequiredArgsConstructor
public class RedirectController {

    private final RedisCache redisCache;

    @GetMapping(value = "/{shorter}")
    public Mono<Void> redirect(@PathVariable String shorter, ServerHttpResponse response) {
        return redisCache.findByShorter(shorter)
                .flatMap(e ->
                {
                    response.setStatusCode(TEMPORARY_REDIRECT);
                    response.getHeaders().setLocation(URI.create(e.getUrlFull()));
                    return response.setComplete();
                });
    }

}
