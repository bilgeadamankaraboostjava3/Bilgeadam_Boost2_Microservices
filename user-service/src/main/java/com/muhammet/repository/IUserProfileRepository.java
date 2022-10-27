package com.muhammet.repository;

import com.muhammet.repository.entity.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserProfileRepository extends MongoRepository<UserProfile,String> {

    @Query("select COUNT(a)>0 from UserProfile a where  a.authid = ?1")
    Boolean isExists(Long authid);

    List<UserProfile> findOptionalByAuthid(Long authid);

}
