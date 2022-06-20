package com.meli.shorter.domain;

public final class ShorterURL {

    public static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final String url;

    private ShorterURL(String url) {
        this.url = url;
    }

    public static ShorterURL create() {
        return new ShorterURL(randomChars());
    }

    private static String randomChars() {
        StringBuilder randomStr = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            randomStr.append(CHARACTERS.charAt((int) Math.floor(Math.random() * CHARACTERS.length())));
        }
        return randomStr.toString();
    }

    public String value() {
        return url;
    }
}
