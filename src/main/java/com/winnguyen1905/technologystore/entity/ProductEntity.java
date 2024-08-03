package com.winnguyen1905.technologystore.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.*;
import com.winnguyen1905.technologystore.common.ProductTypeConstant;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity
@Table(name = "product")
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
    private ShopEntity shop;

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