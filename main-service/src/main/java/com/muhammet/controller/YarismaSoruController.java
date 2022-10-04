package com.muhammet.controller;

import com.muhammet.dto.request.YarismaSoruRequestDto;
import com.muhammet.exception.ErrorType;
import com.muhammet.exception.MainManagerException;
import com.muhammet.repository.entity.YarismaSorulari;
import com.muhammet.service.YarismaSorulariService;
import com.muhammet.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import static com.muhammet.constants.ApiUrls.*;
@RestController
@RequestMapping(BASE_URL+MAIN+YARISMA_SORU)
@RequiredArgsConstructor
public class YarismaSoruController {

    private final YarismaSorulariService yarismaSoruService;
    private final JwtTokenManager jwtTokenManager;

    @PostMapping(CREATE)
    public ResponseEntity<YarismaSorulari> createYarismaSoru(YarismaSoruRequestDto dto) {
      try {
          Optional<Long> userId = jwtTokenManager.getUserId(dto.getToken());
          if(userId.isEmpty()) throw  new MainManagerException(ErrorType.INVALID_TOKEN);
            return ResponseEntity.ok(yarismaSoruService.createYarismaSoru(dto));
      } catch (Exception e) {
        throw  new MainManagerException(ErrorType.INVALID_TOKEN);
      }
    }

}
