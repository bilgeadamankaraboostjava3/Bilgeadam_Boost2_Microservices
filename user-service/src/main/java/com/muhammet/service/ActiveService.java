package com.muhammet.service;

import com.muhammet.repository.IActiveRepository;
import com.muhammet.repository.entity.Active;
import com.muhammet.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class ActiveService extends ServiceManager<Active,String> {
    private final IActiveRepository iActive;

    public ActiveService(IActiveRepository iActive) {
        super(iActive);
        this.iActive = iActive;
    }

    public Active findByQuestionid(String questionid){
        return iActive.findByQuestionid(questionid);
    }

    public void setiActive(String questionid,Boolean isactive){
        Active active = iActive.findByQuestionid(questionid);
        if(active==null){
            iActive.save(Active.builder()
                    .questionid(questionid)
                    .isactive(isactive)
                    .build());
        }else{
            active.setIsactive(isactive);
            iActive.save(active);
        }
    }
}

