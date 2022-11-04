package com.devcamp.task62projectprovinces.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.task62projectprovinces.model.CProvince;

public interface IProvinceRepository extends JpaRepository<CProvince, Long> {

    Optional<CProvince> findByCode(String code);

    void deleteByCode(String code);
    
}


