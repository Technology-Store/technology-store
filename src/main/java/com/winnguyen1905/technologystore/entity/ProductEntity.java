package com.winnguyen1905.technologystore.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.*;
import com.winnguyen1905.technologystore.common.ProductTypeConstant;
import com.winnguyen1905.technologystore.entity.base.BaseEntityAudit;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "p_type", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = SmartPhoneEntity.class, name = ProductTypeConstant.SMARTPHONE),
    @JsonSubTypes.Type(value = LaptopEntity.class, name = ProductTypeConstant.LAPTOP),
    @JsonSubTypes.Type(value = SmartWatchEntity.class, name = ProductTypeConstant.SMARTWATCH)
})
public abstract class ProductEntity extends BaseEntityAudit {

    @Column(name = "p_name", nullable = false)
    private String name;

    @Column(name = "p_thumb", nullable = true)
    private String thumb;

    @Column(name = "p_description", nullable = true)
    private String description;

    @Column(name = "p_price", nullable = false)
    private Double price;

    @Column(name = "p_type", insertable = false, updatable = false)
    private String productType;

    @Column(name = "p_brand", nullable = false)
    private String brand;

    @Column(name = "p_slug", nullable = true, unique = true)
    private String slug;

    @Column(name = "is_draft")
    private Boolean isDraft;

    @Column(name = "is_published")
    private Boolean isPublished;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private UserEntity shop;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST })
    private List<InventoryEntity> inventories = new ArrayList<>();

    @ManyToMany(mappedBy = "products")
    private Set<DiscountEntity> discounts  = new HashSet<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<CartItemEntity> cartItems = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    private List<VariationEntity> variations = new ArrayList<>();

    public void addInventory(InventoryEntity inventory) {
        this.inventories.add(inventory);
    }

    public void addCartItem(CartItemEntity cartItemEntity) {
        this.cartItems.add(cartItemEntity);
    }

    public void removeInventory(InventoryEntity inventory) {
        this.inventories.remove(inventory);
    }

    public void addDiscountCode(DiscountEntity discount) {
        this.discounts.add(discount);
    }

    @PrePersist
    public void prePersist() {
        this.isDraft = true;
        this.isPublished = false;
        super.prePersist();
    }

    @PreUpdate
    public void preUpdate() {
        super.preUpdate();
    }

}