package com.winnguyen1905.technologystore.entity;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class CustomerEntity extends UserEntity {
    // @OneToMany(mappedBy = "customer")
    // private List<CartEntity> carts;
}