package com.meli.shorter.domain;

import com.meli.shorter.infra.utils.URLUtils;
import com.meli.shorter.infra.secondary.dto.DataURL;

public final class ShorterURL {

    public static DataURL create(String fullUrl) {
        return URLUtils.urlShorter(fullUrl);
    }

}
