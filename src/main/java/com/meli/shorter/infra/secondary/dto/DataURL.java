package com.meli.shorter.infra.secondary.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Table("shorter_url")
@JsonIgnoreProperties(value = { "new" })
public class DataURL  implements Persistable<String> {

    @Id
    private String id;
    private String urlFull;
    private String urlShorter;


    public String getId() {
        return id;
    }

    @Override
    @Transient
    public boolean isNew() {
        return id != null;
    }

}
