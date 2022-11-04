package com.devcamp.task62projectprovinces.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.task62projectprovinces.model.CDistrict;
import com.devcamp.task62projectprovinces.model.CWard;

public interface IWardRepository extends JpaRepository<CWard, Long>{

    CWard findByPrefix(String prefix);

   // CDistrict findByDistrict(int id);

    CDistrict findByDistrict(int id);
    
}
