package com.muhammet.service;

import com.muhammet.dto.request.DoLoginRequestDto;
import com.muhammet.dto.request.RegisterRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuthServiceTest {
    @Autowired
    AuthService authService;

    @Test
    public void registerTest(){
        RegisterRequestDto dto = RegisterRequestDto.builder()
                .username("muhammet1")
                .password("1")
                .email("Muhammet")
                .build();
        Assertions.assertEquals(authService.register(dto).getUsername(),"muhammet1");

    }
    @Test
    public void registerTest2(){
        RegisterRequestDto dto = RegisterRequestDto.builder()
                .username("muhammet2")
                .password("1")
                .email("Muhammet")
                .RoleAdminPassword("123456")
                .build();
        Assertions.assertEquals(authService.register(dto).getUsername(),"muhammet2");

    }
    @Test
    public void registerTest3(){
        RegisterRequestDto dto = RegisterRequestDto.builder()
                .username("muhammet3")
                .password("1")
                .email("Muhammet")
                .RoleAdminPassword("4fds32g")
                .build();
        Assertions.assertEquals(authService.register(dto).getUsername(),"muhammet3");

    }
    @Test
    public void dologinTest(){
        DoLoginRequestDto dto = DoLoginRequestDto.builder()
                .username("muhammet")
                .password("1")
                .build();
        authService.dologin(dto);
    }
}
