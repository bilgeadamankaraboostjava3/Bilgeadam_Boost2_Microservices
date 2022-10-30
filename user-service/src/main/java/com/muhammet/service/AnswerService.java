package com.muhammet.service;

import com.muhammet.dto.request.AnswerRequestDto;
import com.muhammet.repository.IAnswerRepository;
import com.muhammet.repository.entity.Answer;
import com.muhammet.repository.entity.UserProfile;
import com.muhammet.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService extends ServiceManager<Answer,String> {
    private final IAnswerRepository iAnswerRepository;
    public AnswerService(IAnswerRepository iAnswerRepository) {
        super(iAnswerRepository);
        this.iAnswerRepository = iAnswerRepository;
    }


    public void save(AnswerRequestDto dto, UserProfile user){
        /**
         * Bu kısım kişinin tekrar yanıtını değiştirmek istemesi durumunfa kullanılmalıdır.
         */
//        Answer answer = iAnswerRepository.findByQuestionidAndUserid(dto.getQuestionid(),user.getId());
//        if(answer==null){
//
//        }
        iAnswerRepository.save(Answer.builder()
                .answer(dto.getAnswer())
                .questionid(dto.getQuestionid())
                .userid(user.getId())
                .date(System.currentTimeMillis())
                .username(user.getName())
                .build());
    }

    public List<Answer> findByQuestionid(String questionid){
        return iAnswerRepository.findByQuestionid(questionid);
    }
}
