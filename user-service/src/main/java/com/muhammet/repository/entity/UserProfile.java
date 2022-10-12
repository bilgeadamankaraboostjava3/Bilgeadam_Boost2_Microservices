package com.muhammet.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document
public class UserProfile implements Serializable {
    @Id
    String id;
    Long authid;
    String username;
    String name;
    String surname;
    String email;
    String phone;
    String photo;
    String address;
    String about;
    Long created;
    Long updated;
    boolean isactive;
}
