package org.muhammet.controller;

import lombok.RequiredArgsConstructor;
import org.muhammet.dto.request.EditProfileRequestDto;
import org.muhammet.dto.request.NewUserCreateDto;
import org.muhammet.exception.ErrorType;
import org.muhammet.exception.UserManagerException;
import org.muhammet.service.UserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.muhammet.constants.ApiUrls.*;
@RestController
@RequestMapping(BASE_URL + USER)
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService userProfileService;

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
        if(dto.getToken()==null)
            throw new UserManagerException(ErrorType.INVALID_TOKEN);
        try{
            Long authid = Long.parseLong(dto.getToken().substring(3,dto.getToken().indexOf("X")));
            return ResponseEntity.ok(userProfileService.updateUserProfile(dto,authid));
        }catch (Exception exception){
            throw new UserManagerException(ErrorType.INVALID_TOKEN);
        }



    }
}
