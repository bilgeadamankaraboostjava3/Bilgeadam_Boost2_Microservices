package com.muhammet.repository.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum State {
    PASIF(0),
    ONAYDA(1),
    ONAYLANMIS(2),
    SILINMIS(3),
    ENGELLENMIS(4);

    private int value;

}
