package com.winnguyen1905.technologystore.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.winnguyen1905.technologystore.common.ProductType;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity
@Table(name = "product")
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = SmartPhoneEntity.class, name = "SMARTPHONE"),
    @JsonSubTypes.Type(value = LaptopEntity.class, name = "LAPTOP"),
    // @JsonSubTypes.Type(value = SmartWatchEntity.class, name = "SMARTWATCH")
})
public abstract class ProductEntity extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "thumb", nullable = true)
    private String thumb;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @Enumerated(EnumType.STRING)    
    @Column(insertable = false, updatable = false, name = "type", nullable = true)
    private ProductType type;

    @Column(name = "brand", nullable = false)
    private String brand;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private ShopEntity shop;
}