package com.muhammet.mapper;

import com.muhammet.dto.request.YarismaRequestDto;
import com.muhammet.repository.entity.Yarisma;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IYarismaMapper {

    IYarismaMapper INSTANCE  = Mappers.getMapper(IYarismaMapper.class);

    @Mappings({
            @Mapping(source = "userid", target = "yarismasahibiuserid"),
            @Mapping(source = "baslik", target = "yarismabaslik"),
            @Mapping(source = "tanim", target = "yarismatanimi")
    })
    Yarisma toYarisma(YarismaRequestDto dto);
}
