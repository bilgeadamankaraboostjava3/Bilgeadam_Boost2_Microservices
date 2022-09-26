package com.muhammet.dto.request;

import com.muhammet.repository.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterRequestDto {
    @NotNull
    @Size(min = 3, max = 20, message = "Kullanıcı adı enaz 3 karater ve en fazla 20 karakter olabilir.")
    String username;
    @NotNull
    String password;
    @NotNull
    @Email(message = "Email formatı uygun değil.")
    String email;
    Roles role;
    String RoleAdminPassword;
}
