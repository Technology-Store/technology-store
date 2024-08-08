package com.winnguyen1905.technologystore.entity;

import com.winnguyen1905.technologystore.common.ProductTypeConstant;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "laptops")
@DiscriminatorValue(ProductTypeConstant.LAPTOP)
@PrimaryKeyJoinColumn(name = "laptop_id")
public class LaptopEntity extends ProductEntity {

    // @Column(name = "size", nullable = false)
    // private double size;

    // @Column(name = "os", nullable = false)
    // private String os;

    // @Column(name = "ram", nullable = false)
    // private int ram;

    // @Column(name = "rom", nullable = false)
    // private int rom;

    // @Column(name = "pin", nullable = false)
    // private int pin;

    // @Column(name = "cpu", nullable = false)
    // private String cpu;

    // @Column(name = "gpu", nullable = false)
    // private String gpu;

}