package com.winnguyen1905.technologystore.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "city")
public class CityEntity extends LocationEntity {
    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<DistrictEntity> districts;
}