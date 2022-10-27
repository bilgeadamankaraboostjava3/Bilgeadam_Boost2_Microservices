package com.muhammet.controller;

import com.muhammet.dto.request.DoLoginRequestDto;
import com.muhammet.dto.request.RegisterRequestDto;
import com.muhammet.dto.response.DoLoginResponseDto;
import com.muhammet.dto.response.RegisterResponseDto;
import com.muhammet.repository.entity.Auth;
import com.muhammet.service.AuthService;
import com.muhammet.config.security.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public String getTestString(){
        return "Auth test";
    }

    @CrossOrigin(originPatterns = "*")
    @PostMapping(LOGIN)
    public ResponseEntity<DoLoginResponseDto> doLogin(@RequestBody @Valid DoLoginRequestDto dto){
        Optional<Auth> auth = authService.dologin(dto);
       if(auth.isPresent()){
           String token = jwtTokenManager.createToken(auth.get().getId()).get();
           return ResponseEntity.ok(DoLoginResponseDto
                   .builder()
                           .token(token)
                           .message("Login successful")
                            .responsecode(200L)
                   .build()
           );
       }
       return ResponseEntity.ok(DoLoginResponseDto
               .builder()
               .message("Login failed")
               .responsecode(400L)
               .build()
       );
    }
    @CrossOrigin(originPatterns = "*")
    @PostMapping(REGISTER)
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto){
        try{
          Auth auth =  authService.register(dto);
          if(auth.getId()==null)
                return ResponseEntity.ok(RegisterResponseDto
                        .builder()
                        .message("Register failed")
                        .responsecode(400L)
                        .build()
                );
            return ResponseEntity.ok(RegisterResponseDto
                    .builder()
                    .message("Register successful")
                    .responsecode(200L)
                    .build());
        }catch (Exception e){
            return ResponseEntity.ok(RegisterResponseDto
                    .builder()
                    .message("Register failed")
                    .responsecode(401L)
                    .build()
            );
        }

    }

}
