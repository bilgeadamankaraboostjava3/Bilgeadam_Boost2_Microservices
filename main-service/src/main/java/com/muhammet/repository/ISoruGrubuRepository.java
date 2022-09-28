package com.muhammet.repository;


import com.muhammet.repository.entity.SoruGrubu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISoruGrubuRepository extends JpaRepository<SoruGrubu, Long> {
}
