package com.muhammet.repository.entity;

import javax.persistence.Embeddable;

@Embeddable
public class TableAdd {

    /**
     * Ortak olan alanlar
     */
    /**
     * 0-> pasif
     * 1-> onayda
     * 2-> onaylanmış
     * 3-> silinmiş
     * 4-> engellenmiş
     */
    Long createdate;
    Long updatedate;
}
