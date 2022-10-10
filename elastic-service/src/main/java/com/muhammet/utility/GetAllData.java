package com.muhammet.utility;

import com.muhammet.manager.IUserManager;
import com.muhammet.repository.entity.UserProfile;
import com.muhammet.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllData {

    private final UserProfileService userProfileService;
    private final IUserManager userManager;

    @PostConstruct
    public void init(){
        List<UserProfile> lists = userManager.findAll().getBody().subList(1,400);
        userProfileService.saveAll(lists);
    }

}
