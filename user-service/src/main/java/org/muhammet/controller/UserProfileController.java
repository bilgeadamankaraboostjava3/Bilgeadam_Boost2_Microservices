package org.muhammet.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.muhammet.dto.request.EditProfileRequestDto;
import org.muhammet.dto.request.NewUserCreateDto;
import org.muhammet.exception.ErrorType;
import org.muhammet.exception.UserManagerException;
import org.muhammet.repository.entity.UserProfile;
import org.muhammet.service.UserProfileService;
import org.muhammet.utility.JwtTokenManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.Optional;


import static org.muhammet.constants.ApiUrls.*;
@RestController
@RequestMapping(BASE_URL + USER)
@RequiredArgsConstructor
@Slf4j
public class UserProfileController {
    private final UserProfileService userProfileService;
    private final JwtTokenManager jwtTokenManager;


    @PostMapping(NEW_CREATE_USER)
    public ResponseEntity<Boolean> NewUserCreate(@RequestBody @Valid NewUserCreateDto dto){
        try{
            userProfileService.createUserProfile(dto);
            return ResponseEntity.ok(true);
        }catch (Exception e){
            throw new UserManagerException(ErrorType.USER_DONT_CREATE);
        }
    }

    @PostMapping(UPDATE_PROFILE)
    public ResponseEntity<Boolean> updateProfile(@RequestBody @Valid EditProfileRequestDto dto){

        try{
            Optional<Long> authid  =jwtTokenManager.getUserId(dto.getToken());
            if(authid.isEmpty())  throw new UserManagerException(ErrorType.INVALID_TOKEN);
            return ResponseEntity.ok(userProfileService.updateUserProfile(dto,authid.get()));
        }catch (Exception exception){
            throw new UserManagerException(ErrorType.INVALID_TOKEN);
        }



    }

    @GetMapping(FINDALL)
    public ResponseEntity<Page<UserProfile>> findAll(int currentPage, int pageSize,  String sortParameter, String direction){
        return ResponseEntity.ok(userProfileService.findAllPage(currentPage,pageSize, sortParameter, direction));
    }

    @GetMapping(FINDALL_SLICE)
    public ResponseEntity<Slice<UserProfile>> findAllSlice(int currentPage, int pageSize, String sortParameter, String direction){
        return ResponseEntity.ok(userProfileService.findAllSlice(currentPage,pageSize, sortParameter, direction));
    }

    @GetMapping("/findallcriteria/{page}/{size}/{sortparameter}/{direction}")
    public ResponseEntity<Slice<UserProfile>> findAllCriteria(@PathVariable int page,
                                                              @PathVariable int size,
                                                              @PathVariable String sortparameter,
                                                              @PathVariable String direction){
        return ResponseEntity.ok(userProfileService.findAllSlice(page,size, sortparameter, direction));
    }

    @GetMapping("/redis")
    @Cacheable(value = "hello_redis")
    public String helloRedis(String message){
        try{
            Thread.sleep(3000);
            return "Mesajınız.....: "+ message;
        }catch (Exception e){
            log.error("Hata: "+e.getMessage());
            return "HATA";
        }
    }

    @GetMapping("/getall")
    public List<UserProfile> getAll(){
       log.info("Tüm Kullanıcılar getirildi..........");
        return userProfileService.getAllCache();
    }

    @GetMapping("/clearcache")
    public void ClearCache(String key,String parameter){
        userProfileService.clearCache(key,parameter);
    }

}
