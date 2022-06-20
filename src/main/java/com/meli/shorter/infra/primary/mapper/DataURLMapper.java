package com.meli.shorter.infra.primary.mapper;

import com.meli.shorter.infra.secondary.dto.DataRedis;
import com.meli.shorter.infra.secondary.dto.DataURL;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DataURLMapper {

    DataURL toData(DataRedis dataRedis);

    DataRedis toRedis(DataURL dataRedis);

}
