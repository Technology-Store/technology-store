package com.winnguyen1905.technologystore.entity;

import com.winnguyen1905.technologystore.entity.base.BaseEntityAudit;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order_items")
public class OrderItemEntity extends BaseEntityAudit {
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "variation_id")
    private VariationEntity productVariation;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Double price;
}