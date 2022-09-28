package com.muhammet.service;


import com.muhammet.dto.request.CevaplarRequestDto;
import com.muhammet.repository.ICevaplarRepository;
import com.muhammet.repository.entity.Cevaplar;
import com.muhammet.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CevaplarService extends ServiceManager<Cevaplar,Long> {
    private final ICevaplarRepository cevaplarRepository;

    public CevaplarService(ICevaplarRepository cevaplarRepository) {
        super(cevaplarRepository);
        this.cevaplarRepository = cevaplarRepository;
    }

    public void soruCevaplariniEkle(List<CevaplarRequestDto> dto, Long soruId){
       dto.forEach(cevap->{
        save(Cevaplar.builder()
                .soru_id(soruId)
                .istrue(cevap.isDogrucevapmi())
                .cevap(cevap.getCevapicerigi())
                .build());
       });
    }
}
