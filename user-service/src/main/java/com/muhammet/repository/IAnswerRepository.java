package com.muhammet.repository;

import com.muhammet.repository.entity.Answer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAnswerRepository extends MongoRepository<Answer,String> {

    Answer findByQuestionidAndUserid(String questionid,String userid);

    List<Answer> findByQuestionid(String questionid);
}
