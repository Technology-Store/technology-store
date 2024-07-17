package com.winnguyen1905.technologystore.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "shop")
public class ShopEntity extends BaseEntity {
    @Column(name = "username", nullable = false)
    private String userName;

    @Column(name = "last_name", nullable = false)
    private String fullName;

    @Column(name = "first_name", nullable = false)
    private String password;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "email", nullable = true)
    private String email;

    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<ProductEntity> products = new ArrayList<ProductEntity>();
}