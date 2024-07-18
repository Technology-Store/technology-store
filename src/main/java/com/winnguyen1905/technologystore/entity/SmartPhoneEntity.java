package com.winnguyen1905.technologystore.entity;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "smartphone")
@DiscriminatorValue("smartphone")
@PrimaryKeyJoinColumn(name = "product_id")
public class SmartPhoneEntity extends ProductEntity implements MyProduct {

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

    @Column(name = "cpu", nullable = false)
    private String cpu;

    @Column(name = "gpu", nullable = false)
    private String gpu; 
}