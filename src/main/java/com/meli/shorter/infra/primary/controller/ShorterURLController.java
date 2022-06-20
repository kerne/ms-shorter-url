package com.meli.shorter.infra.primary.controller;

import com.meli.shorter.application.CreateShorterURL;
import com.meli.shorter.application.RemoveShorterURL;
import com.meli.shorter.application.FindShorterURL;
import com.meli.shorter.infra.primary.dto.RequestURL;
import com.meli.shorter.infra.secondary.dto.DataURL;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public final class ShorterURLController {

    private final CreateShorterURL createShorterURL;
    private final FindShorterURL findShorterURL;
    private final RemoveShorterURL removeShorterURL;


    @PostMapping("/")
    public Mono<ResponseEntity<DataURL>> create(@Valid @RequestBody RequestURL requestURL) {
        return findShorterURL.findById(requestURL.getUrl())
                .switchIfEmpty(createShorterURL.create(requestURL.getUrl()))
                .flatMap(dataURL -> Mono.just(ResponseEntity.status(HttpStatus.CREATED).body(dataURL)))
                .onErrorReturn(ResponseEntity.noContent().build());
    }

    @GetMapping("/")
    public Mono<DataURL> find(@Valid @RequestParam("uri") String uri) {
        return findShorterURL.findByShorter(uri);
    }

    @DeleteMapping("/")
    public Mono<ResponseEntity<Void>> delete(@Valid @RequestParam("uri") String uri) {
        return removeShorterURL.delete(uri).flatMap(e ->Mono.just(ResponseEntity.status(HttpStatus.ACCEPTED).build()));
    }

}