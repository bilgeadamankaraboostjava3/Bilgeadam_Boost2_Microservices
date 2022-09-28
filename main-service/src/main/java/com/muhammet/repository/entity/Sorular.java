package com.muhammet.repository.entity;

import com.muhammet.repository.enums.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "tblsorular")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Sorular {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(length = 1000)
    String soru;
    int sure;
    Long sahipuserid;
    int cevapadedi;
    String grupadi;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    State state = State.ONAYDA;
    @Embedded
    TableAdd tableAdd;

}
