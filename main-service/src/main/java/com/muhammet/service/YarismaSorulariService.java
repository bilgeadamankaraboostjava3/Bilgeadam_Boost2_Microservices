package com.muhammet.service;

import com.muhammet.repository.IYarismaSorulariRepository;
import com.muhammet.repository.entity.YarismaSorulari;
import com.muhammet.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class YarismaSorulariService extends ServiceManager<YarismaSorulari,Long> {
    private final IYarismaSorulariRepository yarismaSorulariRepository;

    public YarismaSorulariService(IYarismaSorulariRepository yarismaSorulariRepository) {
        super(yarismaSorulariRepository);
        this.yarismaSorulariRepository = yarismaSorulariRepository;
    }
}
