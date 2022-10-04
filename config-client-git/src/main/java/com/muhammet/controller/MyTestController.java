package com.muhammet.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class MyTestController {

    @Value("${myapplication.mesaj}")
    private String testvalue ;

    @GetMapping("")
    public String test(){
        return testvalue;
    }
}
