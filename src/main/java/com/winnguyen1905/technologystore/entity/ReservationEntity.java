package com.winnguyen1905.technologystore.entity; 

import com.winnguyen1905.technologystore.entity.base.BaseEntityAudit;

import jakarta.persistence.JoinColumn; 
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationEntity extends BaseEntityAudit {
    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private InventoryEntity inventory;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @JoinColumn(name = "reserved_quantity")
    private Integer reservedQuantity;

    // product ?

    // @ManyToOne
    // @JoinColumn(name = "customer_id")
    // UserEntity customer;
}