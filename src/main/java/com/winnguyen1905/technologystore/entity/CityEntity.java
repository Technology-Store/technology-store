package com.winnguyen1905.technologystore.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.winnguyen1905.technologystore.entity.base.BaseEntityAudit;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cities")
public class CityEntity extends BaseEntityAudit {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", nullable = false)
    private String code;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<DistrictEntity> districts = new HashSet<>();
}