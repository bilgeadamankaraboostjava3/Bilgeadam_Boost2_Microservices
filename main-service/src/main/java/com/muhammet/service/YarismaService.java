package com.muhammet.service;

import com.muhammet.repository.IYarismaRepository;
import com.muhammet.repository.entity.Yarisma;
import com.muhammet.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class YarismaService extends ServiceManager<Yarisma,Long> {
    private final IYarismaRepository yarismaRepository;

    public YarismaService(IYarismaRepository yarismaRepository) {
        super(yarismaRepository);
        this.yarismaRepository = yarismaRepository;
    }
}
