package com.muhammet.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SoruRequestDto {
    private String soruicerigi;
    private int sure;
    private long olusturanid;
    private int cevapadedi;
}
