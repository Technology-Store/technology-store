package com.winnguyen1905.technologystore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "laptop")
@DiscriminatorValue("LAPTOP")
public class LaptopEntity extends ProductEntity implements MyProduct {

    // @Column(name = "size", nullable = false)
    // private Double size;

    // @Column(name = "os", nullable = false)
    // private String os;

    // @Column(name = "ram", nullable = false)
    // private Integer ram;

    // @Column(name = "rom", nullable = false)
    // private Integer rom;

    // @Column(name = "pin", nullable = false)
    // private Integer pin;

    // @Column(name = "cpu", nullable = false)
    // private String cpu;

    // @Column(name = "gpu", nullable = false)
    // private String gpu;

}