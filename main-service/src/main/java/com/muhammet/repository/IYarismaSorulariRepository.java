package com.muhammet.repository;


import com.muhammet.repository.entity.YarismaSorulari;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IYarismaSorulariRepository extends JpaRepository<YarismaSorulari, Long> {
}
