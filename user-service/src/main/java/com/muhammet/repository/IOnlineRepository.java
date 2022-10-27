package com.muhammet.repository;

import com.muhammet.repository.entity.Online;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOnlineRepository extends MongoRepository<Online,String> {

    Optional<Online> findOptionalByUserid(String userid);
    List<Online> findAllByIsonline(Boolean isonline);
}
