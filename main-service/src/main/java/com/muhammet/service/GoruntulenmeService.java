package com.muhammet.service;


import com.muhammet.repository.IGoruntulenmeRepository;
import com.muhammet.repository.entity.Goruntulenme;
import com.muhammet.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class GoruntulenmeService extends ServiceManager<Goruntulenme,Long> {
    private final IGoruntulenmeRepository goruntulenmeRepository;

    public GoruntulenmeService(IGoruntulenmeRepository goruntulenmeRepository) {
        super(goruntulenmeRepository);
        this.goruntulenmeRepository = goruntulenmeRepository;
    }

}
