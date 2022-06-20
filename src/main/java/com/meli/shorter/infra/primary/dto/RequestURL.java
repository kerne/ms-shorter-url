package com.meli.shorter.infra.primary.dto;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public final class RequestURL {
    @URL
    private String url;
}
