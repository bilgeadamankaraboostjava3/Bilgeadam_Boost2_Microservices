package com.muhammet.graphql.mutation;

import com.muhammet.graphql.model.UserProfileInput;
import com.muhammet.repository.entity.UserProfile;
import com.muhammet.service.UserProfileService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProfileMutationResolver
        implements GraphQLMutationResolver {
    private final UserProfileService userProfileService;

    public Boolean createUserProfile(UserProfileInput profile){
      UserProfile user =  userProfileService.save(UserProfile.builder()
                        .id(454_543L)
                        .authid(profile.getAuthid())
                        .username(profile.getUsername())
                        .name(profile.getName())
                        .surname(profile.getSurname())
                        .email(profile.getEmail())
                .build());
        return true;
    }
}
