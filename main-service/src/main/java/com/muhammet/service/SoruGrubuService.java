package com.muhammet.service;


import com.muhammet.repository.ISoruGrubuRepository;
import com.muhammet.repository.entity.SoruGrubu;
import com.muhammet.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class SoruGrubuService extends ServiceManager<SoruGrubu,Long> {
    private final ISoruGrubuRepository soruGrubuRepository;

    public SoruGrubuService(ISoruGrubuRepository soruGrubuRepository) {
        super(soruGrubuRepository);
        this.soruGrubuRepository = soruGrubuRepository;
    }
}
