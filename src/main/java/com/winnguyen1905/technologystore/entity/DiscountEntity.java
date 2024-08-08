package com.winnguyen1905.technologystore.entity;
import java.time.Instant;
import java.util.List;
import java.util.Set;

import com.winnguyen1905.technologystore.common.DiscountAppliesType;
import com.winnguyen1905.technologystore.common.DiscountType;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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

    @Min(value = 0)
    @Column(name = "discount_value")
    private double value;

    @Column(name = "discount_code")
    private String code;

    @Column(name = "discount_start_day")
    private Instant startDate;

    @Column(name = "discount_end_date")
    private Instant endDate;

    @Min(value = 1)
    @Column(name = "discount_max_uses")
    private int maxUses;
    
    @Min(value = 0)
    @Column(name = "discount_uses_count")
    private int usesCount;

    @ManyToMany
    @JoinTable(
        name = "discount_users_used",
        joinColumns = @JoinColumn(name = "discount_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserEntity> customer;

    @Min(value = 1)
    @Column(name = "discount_max_uses_per_user")
    private int maxUsesPerUser;

    @Min(value = 0)
    @Column(name = "discount_min_order_value")
    private double minOrderValue;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private UserEntity shop;

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

    // @ManyToMany(mappedBy = "discounts")
    // private List<CartEntity> carts;

    public void addProduct(ProductEntity product) {
        this.products.add(product);
    }
    
    @PrePersist
    public void prePersist() {
        this.setDiscountType(this.discountType == null ? DiscountType.FIXED_AMOUNT : this.discountType);
        this.setAppliesTo(this.appliesTo == null ? DiscountAppliesType.ALL : this.appliesTo);
        this.setIsActive(this.isActive == null ? false : this.isActive);
        super.prePersist();
    }
}