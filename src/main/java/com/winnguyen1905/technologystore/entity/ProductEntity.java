package com.winnguyen1905.technologystore.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity
@Table(name = "product")
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "p_type", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = SmartPhoneEntity.class, name = "smartphone"),
    @JsonSubTypes.Type(value = LaptopEntity.class, name = "laptop"),
    @JsonSubTypes.Type(value = SmartWatchEntity.class, name = "smartwatch")
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

    // @Enumerated(EnumType.STRING)
    @Column(insertable = false, updatable = false, name = "p_type", nullable = true)
    private String productType;

    @Column(name = "brand", nullable = false)
    private String brand;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private ShopEntity shop;
}