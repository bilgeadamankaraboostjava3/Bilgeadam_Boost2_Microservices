package com.muhammet.rabbitmq.consumer;

import com.muhammet.repository.entity.UserProfile;
import com.muhammet.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.muhammet.rabbitmq.model.CreateUser;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateUserConsumer {

    private  final UserProfileService userProfileService;

    @RabbitListener(queues = "queue-auth-create-user")
    public void createUserMessageConsumer(CreateUser user){
        log.info("User received: {}", user.toString());
        userProfileService.save(UserProfile.builder()
                        .authid(user.getAuthid())
                        .username(user.getUsername())
                        .email(user.getEmail())
                .build());
    }
}
