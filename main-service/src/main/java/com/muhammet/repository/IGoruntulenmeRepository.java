package com.muhammet.repository;


import com.muhammet.repository.entity.Goruntulenme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGoruntulenmeRepository extends JpaRepository<Goruntulenme, Long> {

}
