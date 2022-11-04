package com.devcamp.task62projectprovinces.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcamp.task62projectprovinces.model.CDistrict;
import com.devcamp.task62projectprovinces.model.CWard;
import com.devcamp.task62projectprovinces.repository.IWardRepository;

@Service
public class CWardService {
    @Autowired
    IWardRepository pWardRepository;

    public ArrayList<CWard> getAllWards() {
        ArrayList<CWard> wardsList = new ArrayList<>();
        pWardRepository.findAll().forEach(wardsList::add);
        return wardsList;
    }

    public ArrayList<CWard> getWardsByWardPrefix(String prefix) {
        ArrayList<CWard> wardsList = new ArrayList<>();
        pWardRepository.findAll().forEach(wardsList::add);
        ArrayList<CWard> wardsListByPrefix = new ArrayList<>();
        for(CWard ward:wardsList) {
            if(Objects.equals(ward.getPrefix().toLowerCase(), prefix.toLowerCase()))
                wardsListByPrefix.add(ward);
        }
    return wardsListByPrefix;

}

    public Set<CWard> getWardsByDistrictId(int id) {
        CDistrict vDistrict = pWardRepository.findByDistrict(id);
        if (vDistrict != null)
            return vDistrict.getWards();
        else 
            return null;
    }
   
}
