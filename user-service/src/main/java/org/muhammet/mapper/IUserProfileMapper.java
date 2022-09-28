package org.muhammet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.muhammet.dto.request.EditProfileRequestDto;
import org.muhammet.repository.entity.UserProfile;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IUserProfileMapper {

  IUserProfileMapper INSTANCE = Mappers.getMapper(IUserProfileMapper.class);

  UserProfile toUserProfile(final EditProfileRequestDto dto);

}
