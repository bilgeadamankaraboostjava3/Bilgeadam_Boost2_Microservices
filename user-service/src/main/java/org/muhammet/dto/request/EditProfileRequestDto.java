package org.muhammet.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EditProfileRequestDto {
    String username;
    String name;
    String surname;
    String email;
    String phone;
    String photo;
    String address;
    String about;
}