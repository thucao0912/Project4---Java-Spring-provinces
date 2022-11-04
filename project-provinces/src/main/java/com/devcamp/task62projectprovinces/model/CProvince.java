package com.devcamp.task62projectprovinces.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table (name = "province")
public class CProvince {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;
    
    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<CDistrict> districts;

    public CProvince() {
    }

    public CProvince(String name, String code) {
        this.code = code;
        this.name = name;
    }

    public CProvince(int id, String code, String name, Set<CDistrict> districts) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.districts = districts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CDistrict> getDistricts() {
        return districts;
    }

    public void setDistricts(Set<CDistrict> districts) {
        this.districts = districts;
    }

    
}
