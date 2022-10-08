package com.muhammet.service;
import com.muhammet.dto.request.DoLoginRequestDto;
import com.muhammet.dto.request.NewUserCreateDto;
import com.muhammet.dto.request.RegisterRequestDto;
import com.muhammet.manager.IUserManager;
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
    public AuthService(IAuthRepository authRepository, IUserManager userManager) {
        super(authRepository);
        this.authRepository = authRepository;
        this.userManager = userManager;
    }
    public Optional<Auth> dologin(DoLoginRequestDto dto){
        return authRepository.findOptionalByUsernameIgnoreCaseAndPassword(dto.getUsername(),
                dto.getPassword());
    }
    public Auth register(RegisterRequestDto dto){
        Auth auth;
            auth = Auth.builder()
                    .password(dto.getPassword())
                    .username(dto.getUsername())
                    .role(Roles.USER)
                    .build();
            if(dto.getRoleAdminPassword()!=null)
                if(dto.getRoleAdminPassword().equals("123456"))
                    auth.setRole(dto.getRole()==null ? Roles.ADMIN : dto.getRole());
                else
                    auth.setRole(Roles.USER);

        save(auth);
        userManager.NewUserCreate(
                NewUserCreateDto.builder()
                        .authid(auth.getId())
                        .email(dto.getEmail())
                        .username(dto.getUsername())
                        .build()
        );
       return auth;
    }


}
