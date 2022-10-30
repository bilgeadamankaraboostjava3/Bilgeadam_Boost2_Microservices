package com.muhammet.service;

import com.muhammet.dto.request.EditProfileRequestDto;
import com.muhammet.dto.request.NewUserCreateDto;
import com.muhammet.exception.ErrorType;
import com.muhammet.exception.UserManagerException;
import com.muhammet.mapper.IUserProfileMapper;
import com.muhammet.repository.IOnlineRepository;
import com.muhammet.repository.IUserProfileRepository;
import com.muhammet.repository.entity.Online;
import com.muhammet.repository.entity.UserProfile;
import com.muhammet.utility.ServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile,String> {
    private final IUserProfileRepository repository;
    private final IOnlineRepository onlineRepository;
    @Autowired
    private CacheManager cacheManager;
    public UserProfileService(IUserProfileRepository repository, IOnlineRepository onlineRepository) {
        super(repository);
        this.repository = repository;
        this.onlineRepository = onlineRepository;
    }
    public void setData(){
        onlineRepository.findAll().forEach(online -> {
          Optional<UserProfile> userProfile =   repository.findById(online.getUserid());
          if(userProfile.isPresent())
            online.setUsername(userProfile.get().getUsername());
          onlineRepository.save(online);
        });
    }

    public UserProfile findByAuthId(Long authId){
        return repository.findByAuthid(authId);
    }
    public UserProfile online(Long authid){
        UserProfile profile = repository.findByAuthid(authid);
        if(profile==null) throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        Optional<Online> online = onlineRepository.findOptionalByUserid(profile.getId());
        if(online.isEmpty()){
            onlineRepository.save(Online.builder()
                            .name(profile.getName())
                            .photo(profile.getPhoto())
                            .surname(profile.getSurname())
                            .userid(profile.getId())
                            .username(profile.getUsername())
                            .isonline(true)
                    .build());
        }else{
            online.get().setIsonline(true);
            onlineRepository.save(online.get());
        }
        return profile;
    }
    public UserProfile offline(Long authid){
        UserProfile profile = repository.findByAuthid(authid);
        if(profile==null) throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        Optional<Online> online = onlineRepository.findOptionalByUserid(profile.getId());
        if(online.isEmpty()){
            onlineRepository.save(Online.builder()
                    .name(profile.getName())
                    .photo(profile.getPhoto())
                    .surname(profile.getSurname())
                    .username(profile.getUsername())
                    .userid(profile.getId())
                    .isonline(false)
                    .build());
        }else{
            online.get().setIsonline(false);
            onlineRepository.save(online.get());
        }
        return profile;
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
        UserProfile optionalUserProfile = repository.findByAuthid(authid);
        if(optionalUserProfile==null) return false;
        try{
            userProfile.setId(optionalUserProfile.getId());
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
