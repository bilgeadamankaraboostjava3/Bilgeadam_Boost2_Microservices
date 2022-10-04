package com.muhammet.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SoruGrupRequestDto {
    @NotNull
    @Size(min = 16)
    private String token;
    @NotNull // null
    @NotEmpty// [""]
    @NotBlank// ["      "]
    @Size(min = 3)
    private String grupadi;
}
