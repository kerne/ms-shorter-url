package com.meli.shorter.infra.secondary.persistence

import com.meli.shorter.domain.OriginalURL
import com.meli.shorter.domain.ShorterURL
import com.meli.shorter.domain.URLProduct
import com.meli.shorter.infra.secondary.repository.ShorterURLRepository
import com.meli.shorter.objectmother.DataURLObjectMother
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import spock.lang.Specification

class ShorterURLPersistenceTest extends Specification {

    def shorterRepo = Mock(ShorterURLRepository)
    def shortedURL = new ShorterURLPersistence(shorterRepo)

    def "Save"() {
        given:
        def urlProduct = URLProduct.create(OriginalURL.create(""), ShorterURL.create())
        when:
        def monoRepo = shortedURL.save(urlProduct)
        then:
        StepVerifier.create(monoRepo)
                .expectComplete()
    }

    def "Find"() {
        given:
        def urlProduct = "http://meli.cl"
        when:
        shorterRepo.findDataURLByUrlShorter(_) >> Mono.just(DataURLObjectMother.random(urlProduct))
        def monoRepo = shortedURL.find(urlProduct)
        then:
        StepVerifier.create(monoRepo)
                .expectComplete()
    }

    def "when receive a key to delete then remove it"() {
        given:
        def urlProduct = "http://meli.cl"
        when:
        shorterRepo.findDataURLByUrlShorter(_) >> Mono.just(DataURLObjectMother.random(urlProduct))
        shorterRepo.deleteById(_) >> Mono.empty()
        def monoRepo = shortedURL.remove(urlProduct)
        then:
        StepVerifier.create(monoRepo)
                .expectComplete()
    }
}
