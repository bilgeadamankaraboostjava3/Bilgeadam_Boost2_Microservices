package com.muhammet.repository;

import com.muhammet.repository.entity.Active;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IActiveRepository extends MongoRepository<Active,String> {

    Active findByQuestionid(String questionid);
}
