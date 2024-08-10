package com.winnguyen1905.technologystore.entity;

import java.util.HashSet;
import java.util.Set;

import com.winnguyen1905.technologystore.entity.base.BaseEntityAudit;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "addresses")
public class AddressEntity extends BaseEntityAudit {
    
    private String ward;

    private String commune;

    private String detail;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id", nullable = false)
    private DistrictEntity district;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "address", cascade = CascadeType.REMOVE)
    private Set<InventoryEntity> inventories = new HashSet<>();

}