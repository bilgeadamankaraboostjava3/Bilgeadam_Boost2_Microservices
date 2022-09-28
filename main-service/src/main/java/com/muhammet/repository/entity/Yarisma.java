package com.muhammet.repository.entity;


import com.muhammet.repository.enums.State;
import com.muhammet.repository.enums.YarismaDurumu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Table(name = "tblyarisma")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Yarisma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long yarismasahibiuserid;
    String yarismabaslik;
    String yarismatanimi;
    String resim;
    int kazanacakkisisayisi;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    YarismaDurumu yarismaDurumu = YarismaDurumu.AKTIF_KATILIMA_ACIK;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    State state = State.ONAYDA;
    @Embedded
    TableAdd tableAdd;
    @Transient
    List<Sorular> sorulistesi;

}
