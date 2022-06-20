package com.meli.shorter.application

import com.meli.shorter.domain.services.ShorterURLCachePort
import com.meli.shorter.domain.services.ShorterURLPersistencePort
import com.meli.shorter.objectmother.DataURLObjectMother
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import spock.lang.Specification

class RemoveShorterURLTest extends Specification {

    def shorterURLPersistencePort = Mock(ShorterURLPersistencePort)
    def shorterURLCachePort = Mock(ShorterURLCachePort)
    def findShorterURL = Mock(FindShorterURL)
    def removeShortURL = new RemoveShorterURL(shorterURLPersistencePort, shorterURLCachePort, findShorterURL)

    def "when receive a url to remove then remove"() {
        given:
        def uri = "http://meli.cl"
        when:
        findShorterURL.findByShorterURL(_) >> Mono.just(DataURLObjectMother.random(uri))
        shorterURLCachePort.deleteById(_) >> Mono.just(Boolean.TRUE)
        shorterURLPersistencePort.remove(_) >> Mono.empty()
        def removeDataMono = removeShortURL.remove(uri)

        then:

        StepVerifier.create(removeDataMono)
                .expectComplete()

    }
}
