package com.muhammet.service;

import com.muhammet.repository.IOnlineRepository;
import com.muhammet.repository.entity.Online;
import com.muhammet.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OnlineService extends ServiceManager<Online,String> {
    private final IOnlineRepository repository;

    public OnlineService(IOnlineRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public List<Online> findAllByIsonline(){
        return repository.findAllByIsonline(true);
    }
    public List<Online> findAllByOffline(){
        return repository.findAllByIsonline(false);
    }

    public List<Online> findAll(){
        return repository.findAll();
    }


    public void remove(){
        repository.deleteAll();
    }

}
