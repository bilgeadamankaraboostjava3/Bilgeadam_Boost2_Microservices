package com.muhammet.controller;

import com.muhammet.dto.request.DoLoginRequestDto;
import com.muhammet.dto.request.RegisterRequestDto;
import com.muhammet.repository.entity.Auth;
import com.muhammet.service.AuthService;
import com.muhammet.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Optional;

import static com.muhammet.constants.ApiUrls.*;

@RestController
@RequestMapping(BASE_URL+AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtTokenManager jwtTokenManager;

    // http://localhost:9999/v1/api/auth/test
    @GetMapping("/test")
    public String getTestString(){
        return "Auth test";
    }

    @PostMapping(LOGIN)
    public ResponseEntity<String> doLogin(@RequestBody @Valid DoLoginRequestDto dto){
        Optional<Auth> auth = authService.dologin(dto);
       if(auth.isPresent()){
           String token = jwtTokenManager.createToken(auth.get().getId()).get();
           return ResponseEntity.ok(token);
       }
       return ResponseEntity.badRequest().body("Giriş Başarısız");
    }
    @PostMapping(REGISTER)
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequestDto dto){
        authService.register(dto);
        return ResponseEntity.ok().build();
    }

}
