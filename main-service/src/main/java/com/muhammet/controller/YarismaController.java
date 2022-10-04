package com.muhammet.controller;

import com.muhammet.dto.request.YarismaRequestDto;
import com.muhammet.exception.ErrorType;
import com.muhammet.exception.MainManagerException;
import com.muhammet.repository.entity.Yarisma;
import com.muhammet.service.YarismaService;
import com.muhammet.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import static com.muhammet.constants.ApiUrls.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(BASE_URL+MAIN+YARISMA)
@RequiredArgsConstructor
public class YarismaController {
    private final YarismaService yarismaService;
    private final JwtTokenManager jwtTokenManager;

    @PostMapping(CREATE)
    public ResponseEntity<Yarisma> createYarisma(@RequestBody @Valid YarismaRequestDto dto){
        try {
            Optional<Long> userid = jwtTokenManager.getUserId(dto.getToken());
            if(userid.isEmpty()) throw new MainManagerException(ErrorType.INVALID_TOKEN);
            return ResponseEntity.ok(yarismaService.save(dto));
        } catch (Exception e) {
            throw new MainManagerException(ErrorType.INVALID_TOKEN);
        }
    }
}
