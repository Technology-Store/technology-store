package com.winnguyen1905.technologystore.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "carts")
public class CartEntity extends BaseEntityAudit {
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private ShopEntity shop;

    @OneToMany(mappedBy = "cart")
    private List<CartItemEntity> cartItems;

}