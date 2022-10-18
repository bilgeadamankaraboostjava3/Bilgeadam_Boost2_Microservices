package com.muhammet.service;
import com.muhammet.config.security.JwtTokenManager;
import com.muhammet.dto.request.DoLoginRequestDto;
import com.muhammet.dto.request.NewUserCreateDto;
import com.muhammet.dto.request.RegisterRequestDto;
import com.muhammet.manager.IUserManager;
import com.muhammet.rabbitmq.model.CreateUser;
import com.muhammet.rabbitmq.producer.CreateUserProducer;
import com.muhammet.repository.IAuthRepository;
import com.muhammet.repository.entity.Auth;
import com.muhammet.repository.enums.Roles;
import com.muhammet.utility.ServiceManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository authRepository;
    private final IUserManager userManager;
    private final CreateUserProducer createUserProducer;

    private final JwtTokenManager jwtTokenManager;
    public AuthService(IAuthRepository authRepository, IUserManager userManager,
                       CreateUserProducer createUserProducer, JwtTokenManager jwtTokenManager) {
        super(authRepository);
        this.authRepository = authRepository;
        this.userManager = userManager;
        this.createUserProducer = createUserProducer;
        this.jwtTokenManager = jwtTokenManager;
    }

    public Optional<Auth> dologin(DoLoginRequestDto dto){
        String encodedPassword = jwtTokenManager.encryptPassword(dto.getPassword());
        return authRepository.findOptionalByUsernameIgnoreCaseAndPassword(dto.getUsername(),
                encodedPassword);
    }
    public Auth register(RegisterRequestDto dto){
        String encodedPassword = jwtTokenManager.encryptPassword(dto.getPassword());
        Auth auth;
            auth = Auth.builder()
                    .password(encodedPassword)
                    .username(dto.getUsername())
                    .role(Roles.USER)
                    .build();
            if(dto.getRoleAdminPassword()!=null)
                if(dto.getRoleAdminPassword().equals("123456"))
                    auth.setRole(dto.getRole()==null ? Roles.ADMIN : dto.getRole());
                else
                    auth.setRole(Roles.USER);

        save(auth);
        /*
        User Service e kulanıcının profilin oluşması için istek gönderir.
        userManager.NewUserCreate(
                NewUserCreateDto.builder()
                        .authid(auth.getId())
                        .email(dto.getEmail())
                        .username(dto.getUsername())
                        .build()
        );

         */
        createUserProducer.sendCreateUserMessage(CreateUser.builder()
                        .authid(auth.getId())
                        .email(dto.getEmail())
                        .username(dto.getUsername())
                        .password(encodedPassword)
                .build());
       return auth;
    }


}
