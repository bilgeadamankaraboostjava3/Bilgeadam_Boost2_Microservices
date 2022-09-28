package org.muhammet.repository;

import org.muhammet.repository.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserProfileRepository extends JpaRepository<UserProfile,Long> {

    @Query("select COUNT(a)>0 from UserProfile a where  a.authid = ?1")
    Boolean isExists(Long authid);

    Optional<UserProfile> findOptionalByAuthid(Long authid);

}
