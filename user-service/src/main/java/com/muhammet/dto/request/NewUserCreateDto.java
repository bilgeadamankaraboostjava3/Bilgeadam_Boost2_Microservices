package com.muhammet.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NewUserCreateDto {
    /**
     * yeni üyelik açmış birisinin auth_id bilgisinin tutulduğu alan
     */
    Long authid;
    String username;
    String email;
}
