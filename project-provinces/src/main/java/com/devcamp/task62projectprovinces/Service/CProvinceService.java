package com.devcamp.task62projectprovinces.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcamp.task62projectprovinces.model.CDistrict;
import com.devcamp.task62projectprovinces.model.CProvince;
import com.devcamp.task62projectprovinces.repository.IProvinceRepository;

@Service
public class CProvinceService {
    @Autowired
    IProvinceRepository pProvinceRepository;

    public ArrayList<CProvince> getAllProvinces() {
        ArrayList<CProvince> provincesList = new ArrayList<>();
        pProvinceRepository.findAll().forEach(provincesList::add);
        return provincesList;
    }

    public Set<CDistrict> getDistrictsByProvinceCode(String code) {
        Optional<CProvince> vProvince = pProvinceRepository.findByCode(code);
        CProvince _province = vProvince.get();
        if (_province != null)
            return _province.getDistricts();
        else 
            return null;
    }
}
