package com.muhammet.repository.entity;

import com.muhammet.repository.enums.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "tblgoruntulenme")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Goruntulenme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long user_id;
    Long soru_id;
    Long date;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    State state = State.ONAYLANMIS;
    @Embedded
    TableAdd tableAdd;

}
