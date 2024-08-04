package com.winnguyen1905.technologystore.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "district")
public class DistrictEntity extends LocationEntity {
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "city_id")
    private CityEntity city;

    private String ward;

    private String detail;

    @OneToMany(mappedBy = "district")
    private List<InventoryEntity> inventories;
}