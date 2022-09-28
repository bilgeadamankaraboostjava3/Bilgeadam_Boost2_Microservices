package org.muhammet.service;

import org.muhammet.dto.request.EditProfileRequestDto;
import org.muhammet.dto.request.NewUserCreateDto;
import org.muhammet.mapper.IUserProfileMapper;
import org.muhammet.repository.IUserProfileRepository;
import org.muhammet.repository.entity.UserProfile;
import org.muhammet.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {
    private final IUserProfileRepository repository;
    public UserProfileService(IUserProfileRepository repository) {
        super(repository);
        this.repository = repository;
    }
    public UserProfile createUserProfile(NewUserCreateDto dto){
        return save(UserProfile.builder()
                .authid(dto.getAuthid())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .build());
    }

    public Boolean updateUserProfile(EditProfileRequestDto dto, Long authid){
        UserProfile userProfile = IUserProfileMapper.INSTANCE.toUserProfile(dto);
        Optional<UserProfile> optionalUserProfile = repository.findOptionalByAuthid(authid);
        if(optionalUserProfile.isEmpty()) return false;
        try{
            userProfile.setId(optionalUserProfile.get().getId());
            update(userProfile);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
