package com.muhammet.controller;

import com.muhammet.repository.entity.Online;
import com.muhammet.service.OnlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.muhammet.constants.ApiUrls.*;

@RestController
@RequestMapping(BASE_URL+ONLINE)
@RequiredArgsConstructor
public class OnlineController {

    private final OnlineService onlineService;

    @GetMapping(FINDALL_ONLINE)
    @CrossOrigin(originPatterns = "*")
    public ResponseEntity<List<Online>> findAllOnline(){
        return ResponseEntity.ok(onlineService.findAllByIsonline());
    }

    @GetMapping(FINDALL_OFFLINE)
    @CrossOrigin(originPatterns = "*")
    public ResponseEntity<List<Online>> findAllOffline(){
        return ResponseEntity.ok(onlineService.findAllByOffline());
    }

    @GetMapping(FINDALL)
    @CrossOrigin(originPatterns = "*")
    public ResponseEntity<List<Online>> findAll(){
        return ResponseEntity.ok(onlineService.findAll());
    }


    @GetMapping("/remove")
    public void remove(){
        onlineService.remove();
    }

}
