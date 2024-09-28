package com.winnguyen1905.technologystore.entity;

import java.time.Instant;

import com.winnguyen1905.technologystore.entity.base.BaseEntityAudit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "shippings")
public class ShippingEntity extends BaseEntityAudit {

    @Column(name = "shipping_tracking_number", length = 100)
    private String trackingNumber;

    @Column(name = "shipped_date")
    private Instant shippedDate;

    @Column(name = "delivered_date")
    private Instant deliveredDate;

    @ManyToOne
    @JoinColumn(name = "logistics_provider_id")
    private LogisticsProviderEntity logisticsProvider;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;
    
}