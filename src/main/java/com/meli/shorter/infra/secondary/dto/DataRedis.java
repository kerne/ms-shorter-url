package com.meli.shorter.infra.secondary.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("shorter_url")
public class DataRedis implements Serializable {

    @Id
    private String id;
    private String urlFull;
    private String urlShorter;

}
