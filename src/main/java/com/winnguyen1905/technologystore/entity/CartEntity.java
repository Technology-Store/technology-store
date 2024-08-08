package com.winnguyen1905.technologystore.entity;

import java.util.ArrayList;
import java.util.List;

import com.winnguyen1905.technologystore.entity.base.BaseEntityAudit;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "carts")
public class CartEntity extends BaseEntityAudit {

    // @ManyToMany
    // @JoinTable(
    //     name = "cart_discounts",
    //     joinColumns = @JoinColumn(name = "cart_id"),
    //     inverseJoinColumns = @JoinColumn(name = "discount_id")
    // )
    // private List<DiscountEntity> discounts;
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private UserEntity customer;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private UserEntity shop;

    @OneToMany(mappedBy = "cart", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<CartItemEntity> cartItems = new ArrayList<>();

    public void addCartItems(CartItemEntity cartItemEntity) {
        this.cartItems.add(cartItemEntity);
    }

}