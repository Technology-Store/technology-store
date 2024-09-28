package com.winnguyen1905.technologystore.entity;
import java.util.ArrayList;
import java.util.List;

import com.winnguyen1905.technologystore.entity.base.BaseEntityAudit;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "variations")
public class VariationEntity extends BaseEntityAudit {
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST })
    private List<InventoryEntity> inventories = new ArrayList<>();

    @OneToMany(mappedBy = "variation", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST })
    private List<CartItemEntity> cartItems = new ArrayList<>();
}