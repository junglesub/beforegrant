package com.thc.sprboot.repository;

import com.thc.sprboot.domain.Tbgrantpart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbgrantpartRepository extends JpaRepository<Tbgrantpart, String> {
    Tbgrantpart findByTbgrantIdAndTargetAndFunc(String tbgrantId, String target, String func);
}
