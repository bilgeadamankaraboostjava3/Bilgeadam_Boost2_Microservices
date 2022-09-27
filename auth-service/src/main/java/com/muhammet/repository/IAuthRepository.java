package com.muhammet.repository;

import com.muhammet.repository.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAuthRepository extends JpaRepository<Auth,Long> {

    /**
     * KUllanıcı adı Büyük küçük harf duyarlı olmamalı. (muhammet, Muhammet, MUHAMMET)
     * Şifre büyük küçük harf duyarlı olmalı. (Aswe12! , aswe12!) -> 100^8
     */
    Optional<Auth> findOptionalByUsernameIgnoreCaseAndPassword(String username, String password);
    @Query("select COUNT(a)>0 from Auth a where  UPPER(a.username) =  UPPER(?1) and a.password = ?2")
    Boolean isExists(String username,String password);

}
