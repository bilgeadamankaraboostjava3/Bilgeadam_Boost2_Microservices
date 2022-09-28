package com.muhammet.service;

import com.muhammet.repository.IKatilimRepository;
import com.muhammet.repository.entity.Katilim;
import com.muhammet.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class KatilimService extends ServiceManager<Katilim,Long> {
    private final IKatilimRepository katilimRepository;

    public KatilimService(IKatilimRepository katilimRepository) {
        super(katilimRepository);
        this.katilimRepository = katilimRepository;
    }
}
