package com.meli.shorter.domain;

public class URLProduct {

    private final OriginalURL originalURL;
    private final ShorterURL shorterURL;

    private URLProduct(OriginalURL originalURL, ShorterURL shorterURL) {
        this.originalURL = originalURL;
        this.shorterURL = shorterURL;
    }

    public static URLProduct create(OriginalURL originalURL, ShorterURL shorterURL) {
        return new URLProduct(originalURL, shorterURL);
    }

    public OriginalURL getOriginalURL() {
        return originalURL;
    }

    public ShorterURL getShorterURL() {
        return shorterURL;
    }
}
