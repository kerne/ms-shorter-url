package com.meli.shorter.infra.utils;

import com.meli.shorter.infra.secondary.dto.DataURL;

import java.util.UUID;

public final class URLUtils {

    public static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static DataURL urlShorter(String url) {
        var dataURL = new DataURL();
        dataURL.setId(UUID.randomUUID().toString());
        dataURL.setUrlFull(url);
        dataURL.setUrlShorter(randomChars());
        return dataURL;
    }

    private static String randomChars() {
        StringBuilder randomStr = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            randomStr.append(CHARACTERS.charAt((int) Math.floor(Math.random() * CHARACTERS.length())));
        }
        return randomStr.toString();
    }
}
