package com.muhammet.controller;

import com.muhammet.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.muhammet.constants.ApiUrls.*;

@RestController
@RequestMapping(BASE_URL+AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(LOGIN)
    public ResponseEntity<Void> doLogin(){
        return null;
    }
    @PostMapping(REGISTER)
    public ResponseEntity<Void> register(){
        return null;
    }

}
