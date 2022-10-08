package org.muhammet.service;

import org.muhammet.dto.request.EditProfileRequestDto;
import org.muhammet.dto.request.NewUserCreateDto;
import org.muhammet.mapper.IUserProfileMapper;
import org.muhammet.repository.IUserProfileRepository;
import org.muhammet.repository.entity.UserProfile;
import org.muhammet.utility.ServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {
    private final IUserProfileRepository repository;
    @Autowired
    private CacheManager cacheManager;
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

    public Page<UserProfile> findAllPage(int currentPage, int pageSize, String sortParameter, String direction){
        Sort sort = Sort.by(Sort.Direction.fromString(direction),sortParameter);
        Pageable pageable = PageRequest.of(currentPage,pageSize,sort);
        return repository.findAll(pageable);
    }

    public Slice<UserProfile> findAllSlice(int currentPage, int pageSize, String sortParameter, String direction){
        Sort sort = Sort.by(Sort.Direction.fromString(direction),sortParameter);
        Pageable pageable = PageRequest.of(currentPage,pageSize,sort);
        return repository.findAll(pageable);
    }


    public void clearCache(String key, String parameter){
        cacheManager.getCache(key).evict(parameter);
    }

    /**
     * [Method Adı] :: [Değer] -> id
     * Clear ->
     * @return
     */
    @Cacheable(value = "userprofile_getall")
    public List<UserProfile> getAllCache(){
        return repository.findAll();
    }


    public List<UserProfile> getById(Long id){
        return repository.findAll();
    }

}
