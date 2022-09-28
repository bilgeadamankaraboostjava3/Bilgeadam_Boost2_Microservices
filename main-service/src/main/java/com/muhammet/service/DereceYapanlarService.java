package com.muhammet.service;

import com.muhammet.repository.IDereceYapanlarRepository;
import com.muhammet.repository.entity.DereceYapanlar;
import com.muhammet.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class DereceYapanlarService extends ServiceManager<DereceYapanlar,Long> {
    private final IDereceYapanlarRepository dereceYapanlarRepository;

    public DereceYapanlarService(IDereceYapanlarRepository dereceYapanlarRepository) {
        super(dereceYapanlarRepository);
        this.dereceYapanlarRepository = dereceYapanlarRepository;
    }
}
