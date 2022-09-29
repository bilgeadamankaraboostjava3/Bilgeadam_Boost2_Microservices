package org.muhammet.controller;

import lombok.RequiredArgsConstructor;
import org.muhammet.dto.request.EditProfileRequestDto;
import org.muhammet.dto.request.NewUserCreateDto;
import org.muhammet.exception.ErrorType;
import org.muhammet.exception.UserManagerException;
import org.muhammet.service.UserProfileService;
import org.muhammet.utility.JwtTokenManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.Optional;

import static org.muhammet.constants.ApiUrls.*;
@RestController
@RequestMapping(BASE_URL + USER)
@RequiredArgsConstructor
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
}
