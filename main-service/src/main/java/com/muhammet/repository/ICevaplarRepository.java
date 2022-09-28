package com.muhammet.repository;


import com.muhammet.repository.entity.Cevaplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICevaplarRepository extends JpaRepository<Cevaplar, Long> {
}
