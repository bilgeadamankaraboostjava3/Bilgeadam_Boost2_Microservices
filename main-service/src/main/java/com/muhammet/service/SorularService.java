package com.muhammet.service;


import com.muhammet.dto.request.SoruEklemeRequestDto;
import com.muhammet.repository.ISorularRepository;
import com.muhammet.repository.entity.Sorular;
import com.muhammet.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class SorularService extends ServiceManager<Sorular,Long> {
    private final ISorularRepository sorularRepository;
    private final CevaplarService cevaplarService;
    public SorularService(ISorularRepository sorularRepository, CevaplarService cevaplarService) {
        super(sorularRepository);
        this.sorularRepository = sorularRepository;
        this.cevaplarService = cevaplarService;
    }

    public void soruEkle(SoruEklemeRequestDto dto){
        /**
         * Soruyu kayıt ediyor.
         */
       Sorular soru= save(Sorular.builder()
                .soru(dto.getSoru().getSoruicerigi())
                .cevapadedi(dto.getSoru().getCevapadedi())
                .sure(dto.getSoru().getSure())
                .sahipuserid(dto.getSoru().getOlusturanid())
                .build());
        /**
         * cevapları kayıt etmeniz gerekli.
         * 1. cevap kayıt edebnilmeniz için soru idsine ihtiyaç var
         * 2. bir sorunun en az 2 cevabı(şıkkı) olmalı
         */
        cevaplarService.soruCevaplariniEkle(dto.getCevaplar(),soru.getId());
    }
}
