package com.meli.shorter.domain;

public final class OriginalURL {

    private final String url;

    private OriginalURL(String url) {
        this.url = url;
    }

    public static OriginalURL create(String url) {
        return new OriginalURL(url);
    }

    public String value() {
        return url;
    }
}
