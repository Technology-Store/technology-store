package com.winnguyen1905.technologystore.entity;
import java.util.HashSet;
import java.util.Set;

import com.winnguyen1905.technologystore.common.OrderStatus;
import com.winnguyen1905.technologystore.common.PaymentFrequency;
import com.winnguyen1905.technologystore.common.PaymentMethod;
import com.winnguyen1905.technologystore.entity.base.BaseEntityAudit;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntityAudit {

    @Column(name = "order_code", length = 100)
    private String orderCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false, length = 20)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_payment_method")
    private PaymentMethod paymentMethod; // if paymentMethod is banking create payment service for banking...

    @Enumerated(EnumType.STRING)
    @Column(name = "order_payment_frequency")
    private PaymentFrequency paymentFrequency;
    
    @OneToMany(mappedBy = "order", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    private Set<ShippingEntity> shippings = new HashSet<>();

    @OneToMany(mappedBy = "order", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    private Set<OrderItemEntity> orderItems = new HashSet<>();

}