package com.devcamp.task62projectprovinces.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.task62projectprovinces.Service.CDistrictService;
import com.devcamp.task62projectprovinces.Service.CProvinceService;
import com.devcamp.task62projectprovinces.Service.CWardService;
import com.devcamp.task62projectprovinces.model.CDistrict;
import com.devcamp.task62projectprovinces.model.CProvince;
import com.devcamp.task62projectprovinces.model.CWard;
import com.devcamp.task62projectprovinces.repository.IDistrictRepository;
import com.devcamp.task62projectprovinces.repository.IProvinceRepository;


@RestController
@RequestMapping("/")
@CrossOrigin(value = "*", maxAge = -1)
public class CProvinceController {
    @Autowired
    CProvinceService provinceService;
    @Autowired
    CDistrictService districtService;
    @Autowired
    CWardService wardService;
    @Autowired
    IProvinceRepository provinceRepository;
    @Autowired
    IDistrictRepository districtRepository;

    @GetMapping("/allProvinces")
    public ResponseEntity<List<CProvince>> getAllProvinces() {
        try {
            return new ResponseEntity<>(provinceService.getAllProvinces(),HttpStatus.OK);
        }
        catch(Exception ex) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{provinceCode}/districts")
    public ResponseEntity<Set<CDistrict>> getDisByProvinceCode(@PathVariable(value = "provinceCode") String code) {
        try {
            return new ResponseEntity<>(provinceService.getDistrictsByProvinceCode(code), HttpStatus.OK);
        }
        catch(Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ward")
    public ResponseEntity<Set<CWard>> getWardByDistrictId(@RequestParam(value = "districtId") int disId) {
        try {
            CDistrict vDis = districtRepository.findById(disId);
            
            if(vDis != null) {
            	return new ResponseEntity<>(vDis.getWards(), HttpStatus.OK);
            } else {
            	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{prefix}/wards")
    public ResponseEntity<List<CWard>> getWardsByPrefix(@PathVariable(value = "prefix") String prefix ) {
        try {
            return new ResponseEntity<>(wardService.getWardsByWardPrefix(prefix), HttpStatus.OK);
        }
        catch(Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/province5")
    public ResponseEntity<List<CProvince>> getFiveProvinces(
        @RequestParam(value = "page", defaultValue = "1") String page,
        @RequestParam(value = "size", defaultValue = "5") String size) {
            try {
                PageRequest pageWithFiveElements = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
                List<CProvince> list = new ArrayList<>();
                provinceRepository.findAll(pageWithFiveElements).forEach(list::add);
                return new ResponseEntity<>(list,HttpStatus.OK);
            }
            catch (Exception ex) {
                return null;
            }
        }

    @PostMapping("/createProvince")
    public ResponseEntity<Object> createProvince(@RequestBody CProvince reqProvince) {
        try {
            CProvince _province = new CProvince(reqProvince.getCode(),reqProvince.getName());
            provinceRepository.save(_province);
            return new ResponseEntity<>(_province,HttpStatus.OK);
        }
        catch (Exception ex) {
            return ResponseEntity.unprocessableEntity()
                    .body("Failed to create cpecified province: " + ex.getCause().getCause().getMessage());
        }
    }

    @PutMapping("/updateProvince/{code}")
    public ResponseEntity<Object> updateProvince(@PathVariable(name = "code")String code, @RequestBody CProvince provinceUpdate) {
        Optional<CProvince> _provinceData = provinceRepository.findByCode(code);
        CProvince _province = _provinceData.get();
        if(_province != null) {
            _province.setCode(provinceUpdate.getCode());
            _province.setName(provinceUpdate.getName());
            try {
                return ResponseEntity.ok(provinceRepository.save(_province));
            }
            catch (Exception ex) {
                return ResponseEntity.unprocessableEntity()
                        .body("Cannot execute operation for this entity: " + ex.getCause().getCause().getMessage());
            }
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{code}")
    public ResponseEntity<Object> deleteProvince(@PathVariable(name = "code")String code) {
        //provinceRepository.deleteByCode(code);
        Optional<CProvince> _provinceData = provinceRepository.findByCode(code);
        if(_provinceData.isPresent()) {
            try {
                provinceRepository.delete(_provinceData.get());
                return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
            }
            catch (Exception ex) {
                return ResponseEntity.unprocessableEntity()
                        .body("Cannot execute operation of this entity...");
                        //.body("Cannot execute operation of this entity: " + ex.getCause().getCause().getMessage());
            }
        }
        else {
            return new ResponseEntity<Object>("User not found", HttpStatus.NOT_FOUND);
        }
    }
}
