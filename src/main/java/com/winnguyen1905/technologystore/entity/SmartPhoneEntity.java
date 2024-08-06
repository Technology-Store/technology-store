package com.winnguyen1905.technologystore.entity;

import java.util.List;

import com.winnguyen1905.technologystore.common.ProductTypeConstant;
import com.winnguyen1905.technologystore.util.SlugUtils;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "smartphones")
@DiscriminatorValue(ProductTypeConstant.SMARTPHONE)
@PrimaryKeyJoinColumn(name = "smartphone_id")
public class SmartPhoneEntity extends ProductEntity {
    @Column(name = "x_size", nullable = true)
    private Double xSize;

    @Column(name = "y_size", nullable = true)
    private Double ySize;

    @Column(name = "panel_type", nullable = false)
    private String panelType;

    @Column(name = "resolution", nullable = true)
    private String resolution;

    @Column(name = "os", nullable = false)
    private String os;

    @Column(name = "ram", nullable = false)
    private Integer ram;

    @Column(name = "rom", nullable = false)
    private Integer rom;

    @Column(name = "pin", nullable = false)
    private Integer pin;

    @Column(name = "chipset", nullable = false)
    private String chipset;

    @PrePersist
    public void prePersist() {
        StringBuilder slg = new StringBuilder(SlugUtils.slugGenerator(this));
        super.setSlug(slg.toString());
        super.prePersist();
    }

    @PreUpdate
    public void preUpdate() {
        StringBuilder slg = new StringBuilder(SlugUtils.slugGenerator(this));
        super.setSlug(slg.toString());
        super.preUpdate();
    }
}