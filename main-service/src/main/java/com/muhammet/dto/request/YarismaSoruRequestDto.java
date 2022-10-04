package com.muhammet.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class YarismaSoruRequestDto {
    String token;
    Long soru_id;
    Long yarisma_id;
    Long sure;
}
