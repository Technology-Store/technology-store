package com.winnguyen1905.technologystore.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "shop")
public class ShopEntity extends BaseEntity {

    @Column(name = "username", nullable = false)
    private String userName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "phone", nullable = true)
    private Integer phone;

    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY, orphanRemoval = true, cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    private List<ProductEntity> products = new ArrayList<ProductEntity>();

}