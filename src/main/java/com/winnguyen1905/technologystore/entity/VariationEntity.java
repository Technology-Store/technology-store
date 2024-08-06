package com.winnguyen1905.technologystore.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
}