package com.muhammet.controller;

import com.muhammet.dto.request.DoLoginRequestDto;
import com.muhammet.dto.request.RegisterRequestDto;
import com.muhammet.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.muhammet.constants.ApiUrls.*;

@RestController
@RequestMapping(BASE_URL+AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(LOGIN)
    public ResponseEntity<Void> doLogin(DoLoginRequestDto dto){
        return null;
    }
    @GetMapping(REGISTER)
    public ResponseEntity<Void> register(@Valid RegisterRequestDto dto){
        return null;
    }

}
