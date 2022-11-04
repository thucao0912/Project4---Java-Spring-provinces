package com.devcamp.task62projectprovinces.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name = "district")
public class CDistrict {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "prefix")
    private String prefix;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "province_id")
    private CProvince province;

    @OneToMany(mappedBy = "district",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<CWard> wards;

    public CDistrict() {
    }

    public CDistrict(String name, String prefix) {
        this.name = name;
        this.prefix = prefix;
    }

    public CDistrict(int id, String name, String prefix, CProvince province, Set<CWard> wards) {
        this.id = id;
        this.name = name;
        this.prefix = prefix;
        this.province = province;
        this.wards = wards;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public CProvince getProvince() {
        return province;
    }

    public void setProvince(CProvince province) {
        this.province = province;
    }

    public Set<CWard> getWards() {
        return wards;
    }

    public void setWards(Set<CWard> wards) {
        this.wards = wards;
    }

    
}
