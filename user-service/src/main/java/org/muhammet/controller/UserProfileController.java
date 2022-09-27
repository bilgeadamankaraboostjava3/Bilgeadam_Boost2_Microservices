package org.muhammet.controller;

import lombok.RequiredArgsConstructor;
import org.muhammet.dto.request.NewUserCreateDto;
import org.muhammet.exception.ErrorType;
import org.muhammet.exception.UserManagerException;
import org.muhammet.service.UserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.muhammet.constants.ApiUrls.*;
@RestController
@RequestMapping(BASE_URL + USER)
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService userProfileService;

    @PostMapping(NEW_CREATE_USER)
    public ResponseEntity<Boolean> NewUserCreate(NewUserCreateDto dto){
        try{
            userProfileService.createUserProfile(dto);
            return ResponseEntity.ok(true);
        }catch (Exception e){
            throw new UserManagerException(ErrorType.USER_DONT_CREATE);
        }
    }
}
