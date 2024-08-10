package com.winnguyen1905.technologystore.entity;

import java.util.HashSet;
import java.util.Set;

import com.winnguyen1905.technologystore.entity.base.BaseEntityAudit;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "logistics_providers")
public class LogisticsProviderEntity extends BaseEntityAudit {
    @OneToMany(mappedBy = "logisticsProvider")
    private Set<ShippingEntity> shippings = new HashSet<>();
}