package com.meli.shorter.application

import com.meli.shorter.domain.services.ShorterURLCachePort
import com.meli.shorter.domain.services.ShorterURLPersistencePort
import com.meli.shorter.infra.secondary.dto.DataURL
import com.meli.shorter.objectmother.DataURLObjectMother
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import spock.lang.Specification

class FindShorterURLTest extends Specification {

    def shorterURLPersistencePort = Mock(ShorterURLPersistencePort)
    def shorterURLCachePort = Mock(ShorterURLCachePort)

    def findShorterURL = new FindShorterURL(shorterURLPersistencePort, shorterURLCachePort)

    def "When Finding By Shorter URL then return data url "() {
        given:
        var uri = "http://meli.cl"
        def dataURL = DataURLObjectMother.random(uri)
        when:
        shorterURLCachePort.findByShorterURL(_) >> Mono.empty()
        shorterURLPersistencePort.find(_) >> Mono.just(dataURL)
        def dataShorterURL = this.findShorterURL.findByShorterURL(uri)
        then:
        StepVerifier
                .create(dataShorterURL)
                .expectNext(dataURL)
                .expectComplete()

    }

    def "When Finding By ID then return data url"() {
        given:
        var uri = "http://meli.cl"
        def dataURL = DataURLObjectMother.random(uri)
        when:
        shorterURLCachePort.findByFullURL(_) >> Mono.empty()
        shorterURLPersistencePort.find(_) >> Mono.just(new DataURL())
        def dataShorterURL = this.findShorterURL.findByURL(uri)
        then:
        StepVerifier
                .create(dataShorterURL)
                .expectNext(dataURL)
                .expectComplete()
    }
}
