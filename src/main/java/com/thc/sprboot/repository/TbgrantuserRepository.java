package com.thc.sprboot.repository;

import com.thc.sprboot.domain.Tbgrantuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbgrantuserRepository extends JpaRepository<Tbgrantuser, String> {
    Tbgrantuser findByTbgrantIdAndTbuserId(String tbgrantId, String tbuserId);
}
