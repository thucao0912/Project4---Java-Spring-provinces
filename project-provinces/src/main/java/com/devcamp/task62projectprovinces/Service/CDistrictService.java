package com.devcamp.task62projectprovinces.Service;

import java.util.ArrayList;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcamp.task62projectprovinces.model.CDistrict;
import com.devcamp.task62projectprovinces.repository.IDistrictRepository;

@Service
public class CDistrictService {
    @Autowired
    IDistrictRepository pDistrictRepository;

    public ArrayList<CDistrict> getAllDistricts() {

        ArrayList<CDistrict> districtsList = new ArrayList<>();
        pDistrictRepository.findAll().forEach(districtsList::add);
        return districtsList;
    }

}
