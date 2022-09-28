package org.muhammet.service;

import org.muhammet.dto.request.EditProfileRequestDto;
import org.muhammet.dto.request.NewUserCreateDto;
import org.muhammet.mapper.IUserProfileMapper;
import org.muhammet.repository.IUserProfileRepository;
import org.muhammet.repository.entity.UserProfile;
import org.muhammet.utility.ServiceManager;
import org.springframework.stereotype.Service;

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

    public Boolean updateUserProfile(EditProfileRequestDto dto){
        UserProfile userProfile = IUserProfileMapper.INSTANCE.toUserProfile(dto);
        try{
            update(userProfile);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
