package com.muhammet.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class YarismaRequestDto {
    String token;
    @NotNull
    Long userid;
    @NotNull
    String baslik;
    @NotNull
    String tanim;
    @NotNull
    String resim;
    @NotNull
    int kazanacakkisisayisi;
}
