package com.thc.sprboot.repository;

import com.thc.sprboot.domain.Tbgrant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbgrantRepository extends JpaRepository<Tbgrant, String> {
}
