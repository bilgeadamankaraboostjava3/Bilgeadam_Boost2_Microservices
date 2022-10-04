package com.muhammet.controller;

import com.muhammet.dto.request.SoruGrupRequestDto;
import com.muhammet.exception.ErrorType;
import com.muhammet.exception.MainManagerException;
import com.muhammet.repository.entity.SoruGrubu;
import com.muhammet.service.SoruGrubuService;
import com.muhammet.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.muhammet.constants.ApiUrls.*;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(BASE_URL+MAIN+SORU_GRUP)
@RequiredArgsConstructor
public class SoruGrupController {
    private final JwtTokenManager jwtTokenManager;
    private final SoruGrubuService soruGrubuService;
    @PostMapping(CREATE)
    public ResponseEntity<SoruGrubu> createSoruGrup(@RequestBody @Valid SoruGrupRequestDto dto){
        try{
            Optional<Long> userid = jwtTokenManager.getUserId(dto.getToken());
            if(userid.isEmpty()) throw new MainManagerException(ErrorType.INVALID_TOKEN);
            /**
             * Burada gelen userid nin ilgili işlemi yapabilecek yetkisinin olup olmadığı
             * kontrol edilerek işleme izin verilmelidir.
             */
           return ResponseEntity.ok(soruGrubuService.save(SoruGrubu.builder()
                            .grupadi(dto.getGrupadi())
                            .build()));
        }catch (Exception e){
            throw new MainManagerException(ErrorType.INVALID_TOKEN);
        }
    }
}
