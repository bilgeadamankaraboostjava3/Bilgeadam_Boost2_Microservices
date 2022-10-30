package com.muhammet.utility;

import com.muhammet.repository.IActiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserImpl {

    @Autowired
    IActiveRepository iActive;

    @PostConstruct
    public void test(){
        iActive.deleteAll();
    }

}
