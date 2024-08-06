package com.winnguyen1905.technologystore.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "shops")
@DiscriminatorValue("shop")
public class ShopEntity extends UserEntity {
    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<ProductEntity> products;

    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    private List<DiscountEntity> discount;

    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    private List<InventoryEntity> inventories;
}