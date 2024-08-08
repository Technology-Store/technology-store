package com.winnguyen1905.technologystore.entity;

import com.winnguyen1905.technologystore.entity.base.BaseEntityAudit;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "reservations")
public class ReservationEntity extends BaseEntityAudit {
    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private InventoryEntity inventory;
}