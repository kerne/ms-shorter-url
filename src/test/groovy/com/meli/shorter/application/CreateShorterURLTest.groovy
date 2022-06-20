package com.meli.shorter.application

import com.meli.shorter.domain.services.ShorterURLCachePort
import com.meli.shorter.domain.services.ShorterURLPersistencePort
import com.meli.shorter.objectmother.DataURLObjectMother
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import spock.lang.Specification

class CreateShorterURLTest extends Specification {

    def shorterURLPersistencePort = Mock(ShorterURLPersistencePort)
    def shorterURLCachePort = Mock(ShorterURLCachePort)

    def createShorterURL = new CreateShorterURL(shorterURLPersistencePort, shorterURLCachePort)

    def "when received full url then create a short url"() {
        given:
        def url = "http://meli.cl"
        def dataURL = DataURLObjectMother.random()
        when:
        shorterURLPersistencePort.save(_) >> Mono.just(dataURL)
        shorterURLCachePort.save(_) >> Mono.just(dataURL)
        def createURLMono = createShorterURL.create(url)
        then:
        StepVerifier.create(createURLMono)
                .expectNext(dataURL)
                .expectComplete()
    }
}
