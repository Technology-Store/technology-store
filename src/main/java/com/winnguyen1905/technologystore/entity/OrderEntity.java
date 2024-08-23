package com.winnguyen1905.technologystore.entity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private UserEntity customer;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private UserEntity shop;

    @Column(name = "order_code", length = 100, unique = true)
    private String orderCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", length = 20)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_payment_method")
    private PaymentMethod paymentMethod; // If payment method is banking create payment service for banking...

    @Enumerated(EnumType.STRING)
    @Column(name = "order_payment_frequency")
    private PaymentFrequency paymentFrequency;
    
    @OneToMany(mappedBy = "order", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    private Set<ShippingEntity> shippings = new HashSet<>();

    @OneToMany(mappedBy = "order", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    private List<OrderItemEntity> orderItems = new ArrayList<>();

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @Column(name = "total_ship_price")
    private Double totalShipPrice;

    @Column(name = "total_discount_voucher")
    private Double totalDiscountVoucher;

    @Column(name = "amount_ship_reduced")
    private Double amountShipReduced;

    @Column(name = "amount_product_reduced")
    private Double amountProductReduced;

    @Column(name = "final_price", nullable = false)
    private Double finalPrice;

    @PrePersist
    protected void prePersist() {
        this.orderStatus = OrderStatus.SPENDING;
        super.prePersist();
    }

}