package com.meli.shorter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
public class MsShorterUrlApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsShorterUrlApplication.class, args);
    }

}
