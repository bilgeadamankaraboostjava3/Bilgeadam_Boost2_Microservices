package com.muhammet.service;
import com.muhammet.dto.request.DoLoginRequestDto;
import com.muhammet.dto.request.RegisterRequestDto;
import com.muhammet.repository.IAuthRepository;
import com.muhammet.repository.entity.Auth;
import com.muhammet.repository.enums.Roles;
import com.muhammet.utility.ServiceManager;
import org.springframework.stereotype.Service;
@Service
public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository authRepository;

    public AuthService(IAuthRepository authRepository) {
        super(authRepository);
        this.authRepository = authRepository;
    }
    public boolean dologin(DoLoginRequestDto dto){
        return authRepository.isExists(dto.getUsername(),
                dto.getPassword());
    }
    public Auth register(RegisterRequestDto dto){
        Auth auth;
            auth = Auth.builder()
                    .password(dto.getPassword())
                    .username(dto.getUsername())
                    .build();
            if(dto.getRoleAdminPassword()!=null)
                if(dto.getRoleAdminPassword().equals("123456"))
                    auth.setRole(dto.getRole()==null ? Roles.ADMIN : dto.getRole());
                else
                    auth.setRole(Roles.USER);
       return save(auth);
    }


}
