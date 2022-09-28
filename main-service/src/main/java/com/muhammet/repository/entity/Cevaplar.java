package com.muhammet.repository.entity;

import com.muhammet.repository.enums.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "tlcevaplar")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Cevaplar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long soru_id;
    @Column(length = 1000)
    String cevap;
    boolean istrue;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    State state = State.ONAYLANMIS;
    @Embedded
    TableAdd tableAdd;
}
