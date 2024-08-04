package com.winnguyen1905.technologystore.entity;
import java.time.Instant;
import java.util.List;
import java.util.Set;

import com.winnguyen1905.technologystore.common.DiscountAppliesType;
import com.winnguyen1905.technologystore.common.DiscountType;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "discounts")
public class DiscountEntity extends BaseEntityAudit {
    @Column(name = "discount_name")   
    private String name;

    @Column(name = "discount_description", columnDefinition = "MEDIUMTEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "discount_type")
    private DiscountType discountType;

    @Column(name = "discount_value")
    private Number value;

    @Column(name = "discount_code")
    private String code;

    @Column(name = "discount_start_day")
    private Instant startDate;

    @Column(name = "discount_end_date")
    private Instant endDate;

    @Column(name = "discount_max_uses")
    private Integer maxUses;
    
    @Column(name = "discount_uses_count")
    private Integer usesCount;

    @ManyToMany
    @JoinTable(
        name = "discount_users_used",
        joinColumns = @JoinColumn(name = "discount_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserEntity> users;

    @Column(name = "discount_max_uses_per_user")
    private Integer maxUsesPerUser;

    @Column(name = "discount_min_order_value")
    private Number minOrderValue;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private ShopEntity shop;

    @Column(name = "discount_is_active")
    private Boolean isActive;

    @Enumerated(EnumType.STRING)
    @Column(name = "discount_applies_to")
    private DiscountAppliesType appliesTo;

    @ManyToMany
    @JoinTable(
        name = "discount_products",
        joinColumns = @JoinColumn(name = "discount_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<ProductEntity> products;

    public void addProduct(ProductEntity product) {
        this.products.add(product);
    }
    
    @PrePersist
    public void prePersist() {
        this.discountType = this.discountType == null ? DiscountType.FIXED_AMOUNT : this.discountType;
        this.appliesTo = this.appliesTo == null ? DiscountAppliesType.ALL : this.appliesTo;
        this.isActive = this.isActive == null ? false : this.isActive;
        super.prePersist();
    }
}