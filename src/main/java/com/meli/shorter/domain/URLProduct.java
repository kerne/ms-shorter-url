package com.meli.shorter.domain;

import java.util.UUID;

public final class URLProduct {
    private final OriginalURL originalURL;
    private final ShorterURL shorterURL;
    private final String id;

    private URLProduct(String id, OriginalURL originalURL, ShorterURL shorterURL) {
        this.id = id;
        this.originalURL = originalURL;
        this.shorterURL = shorterURL;
    }

    public static URLProduct create(OriginalURL originalURL, ShorterURL shorterURL) {
        return new URLProduct(UUID.randomUUID().toString(), originalURL, shorterURL);
    }

    public OriginalURL original() {
        return originalURL;
    }

    public ShorterURL shorter() {
        return shorterURL;
    }

    public String id() {
        return id;
    }
}
