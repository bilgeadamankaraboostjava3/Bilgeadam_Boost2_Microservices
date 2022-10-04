package com.muhammet.mapper;

import com.muhammet.dto.request.YarismaSoruRequestDto;
import com.muhammet.repository.entity.YarismaSorulari;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IYarismaSoruMapper {

    IYarismaSoruMapper INSTANCE = Mappers.getMapper(IYarismaSoruMapper.class);

    YarismaSorulari toYarismaSorulari(YarismaSoruRequestDto dto);
}
