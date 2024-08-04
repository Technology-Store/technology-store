package com.winnguyen1905.technologystore.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "inventory")
public class InventoryEntity extends BaseEntityAudit {
    @Column(name = "inven_stock")
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private DistrictEntity district;

    @OneToMany(mappedBy = "inventory")
    private List<ReservationEntity> reservations;
}