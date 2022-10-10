package com.muhammet.repository;

import com.muhammet.repository.entity.UserProfile;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


import java.util.List;
import java.util.Optional;

public interface IUserProfileRepository extends ElasticsearchRepository<UserProfile, Long> {

   // @Query("select COUNT(a)>0 from UserProfile a where  a.authid = ?1")
  //  Boolean isExists(Long authid);

   // Optional<UserProfile> findOptionalByAuthid(Long authid);

    List<UserProfile> findAllByUsername(String userName, Pageable pageable);

    @Query("select u.email from UserProfile u")
    List<String> findAllEmailList();


}
