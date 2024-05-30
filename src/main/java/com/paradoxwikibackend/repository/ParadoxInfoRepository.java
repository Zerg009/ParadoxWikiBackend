package com.paradoxwikibackend.repository;

import com.paradoxwikibackend.model.ParadoxInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParadoxInfoRepository extends JpaRepository<ParadoxInfo,Integer> {

}
