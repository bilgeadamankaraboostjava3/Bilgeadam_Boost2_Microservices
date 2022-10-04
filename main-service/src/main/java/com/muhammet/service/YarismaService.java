package com.muhammet.service;

import com.muhammet.dto.request.YarismaRequestDto;
import com.muhammet.mapper.IYarismaMapper;
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

    public Yarisma save(YarismaRequestDto dto){
        return save(IYarismaMapper.INSTANCE.toYarisma(dto));
    }
}
