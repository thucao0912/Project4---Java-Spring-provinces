package com.devcamp.task62projectprovinces.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.task62projectprovinces.model.CDistrict;

public interface IDistrictRepository extends JpaRepository<CDistrict, Long> {

    CDistrict findById(int id);
    
}
