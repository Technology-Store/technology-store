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
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "is_selected")
    private Boolean isSelected;

    // Variation

}