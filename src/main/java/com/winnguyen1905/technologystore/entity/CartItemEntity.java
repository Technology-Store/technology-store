package com.winnguyen1905.technologystore.entity;

import com.winnguyen1905.technologystore.entity.base.BaseEntityAudit;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cart_items")
public class CartItemEntity extends BaseEntityAudit {

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private CartEntity cart;

    @ManyToOne
    @JoinColumn(name = "variation_id")
    private VariationEntity productVariation;

    @Column(name = "quantity")
    private Integer quantity = 0;

    @Column(name = "is_selected")
    private Boolean isSelected;

    // Variation
}