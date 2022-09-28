package com.muhammet.repository;

import com.muhammet.repository.entity.Yarisma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IYarismaRepository extends JpaRepository<Yarisma, Long> {
}
