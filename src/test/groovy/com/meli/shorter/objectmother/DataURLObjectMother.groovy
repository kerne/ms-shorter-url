package com.meli.shorter.objectmother

import com.meli.shorter.infra.secondary.dto.DataURL

class DataURLObjectMother {

    def static random(String fullURL = "http://meli.cl", String shortURL = "rqG5VDpomk") {
        return new DataURL(UUID.randomUUID().toString(), fullURL, shortURL)
    }

}
