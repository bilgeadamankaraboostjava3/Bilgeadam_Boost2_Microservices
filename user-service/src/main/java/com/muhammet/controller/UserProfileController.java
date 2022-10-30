package com.muhammet.controller;

import com.muhammet.dto.request.NewUserCreateDto;
import com.muhammet.dto.request.OnlineResponseDto;
import com.muhammet.repository.entity.UserProfile;
import com.muhammet.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.muhammet.dto.request.EditProfileRequestDto;
import com.muhammet.exception.ErrorType;
import com.muhammet.exception.UserManagerException;
import com.muhammet.utility.JwtTokenManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.Optional;


import static com.muhammet.constants.ApiUrls.*;
@RestController
@RequestMapping(BASE_URL + USER)
@RequiredArgsConstructor
@Slf4j

public class UserProfileController {
    private final UserProfileService userProfileService;
    private final JwtTokenManager jwtTokenManager;

    @GetMapping("/setdata")
    public void setData(){
        userProfileService.setData();
    }

    @CrossOrigin(originPatterns = "*")
    @PostMapping(ONLINE)
    public ResponseEntity<UserProfile> doOnline(@RequestBody OnlineResponseDto dto){
        try{
            Optional<Long> authid  =jwtTokenManager.getUserId(dto.getToken());
            if(authid.isEmpty())  throw new UserManagerException(ErrorType.INVALID_TOKEN);
            return ResponseEntity.ok(userProfileService.online(authid.get()));
        }catch (Exception exception){
            throw new UserManagerException(ErrorType.INVALID_TOKEN);
        }
    }

    @CrossOrigin(originPatterns = "*")
    @PostMapping(OFFLINE)
    public ResponseEntity<UserProfile> doOffline(@RequestBody OnlineResponseDto dto){
        try{
            Optional<Long> authid  =jwtTokenManager.getUserId(dto.getToken());
            if(authid.isEmpty())  throw new UserManagerException(ErrorType.INVALID_TOKEN);
            return ResponseEntity.ok(userProfileService.offline(authid.get()));
        }catch (Exception exception){
            throw new UserManagerException(ErrorType.INVALID_TOKEN);
        }
    }

    @CrossOrigin(originPatterns = "*")
    @PostMapping(NEW_CREATE_USER)
    public ResponseEntity<Boolean> NewUserCreate(@RequestBody @Valid NewUserCreateDto dto){
        try{
            userProfileService.createUserProfile(dto);
            return ResponseEntity.ok(true);
        }catch (Exception e){
            throw new UserManagerException(ErrorType.USER_DONT_CREATE);
        }
    }
    @CrossOrigin(originPatterns = "*")
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
    @CrossOrigin(originPatterns = "*")
    @GetMapping(FINDALL)
    public ResponseEntity<Page<UserProfile>> findAll(int currentPage, int pageSize, String sortParameter, String direction){
        return ResponseEntity.ok(userProfileService.findAllPage(currentPage,pageSize, sortParameter, direction));
    }
    @CrossOrigin(originPatterns = "*")
    @GetMapping(FINDALL_SLICE)
    public ResponseEntity<Slice<UserProfile>> findAllSlice(int currentPage, int pageSize, String sortParameter, String direction){
        return ResponseEntity.ok(userProfileService.findAllSlice(currentPage,pageSize, sortParameter, direction));
    }
    @CrossOrigin(originPatterns = "*")
    @GetMapping("/findallcriteria/{page}/{size}/{sortparameter}/{direction}")
    public ResponseEntity<Slice<UserProfile>> findAllCriteria(@PathVariable int page,
                                                              @PathVariable int size,
                                                              @PathVariable String sortparameter,
                                                              @PathVariable String direction){
        return ResponseEntity.ok(userProfileService.findAllSlice(page,size, sortparameter, direction));
    }
    @CrossOrigin(originPatterns = "*")
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

    @CrossOrigin(originPatterns = "*")
    @GetMapping("/findAllData")
    public ResponseEntity<List<UserProfile>> findAll(){
        Long start = System.currentTimeMillis();
        System.out.println("Start Time : "+start);
        List<UserProfile> lists = userProfileService.findAll();
        Long end = System.currentTimeMillis();
        System.out.println("End Time : "+end);
        System.out.println("Time UserService....: "+(end-start));
        return ResponseEntity.ok(lists);
    }


    @CrossOrigin(originPatterns = "*")
    @GetMapping("/getall")
    public List<UserProfile> getAll(){
       log.info("Tüm Kullanıcılar getirildi..........");
        return userProfileService.getAllCache();
    }
    @CrossOrigin(originPatterns = "*")
    @GetMapping("/clearcache")
    public void ClearCache(String key,String parameter){
        userProfileService.clearCache(key,parameter);
    }

}
