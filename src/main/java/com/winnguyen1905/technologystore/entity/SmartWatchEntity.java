package com.winnguyen1905.technologystore.entity;

import com.winnguyen1905.technologystore.common.ProductTypeConstant;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "smartwatchs")
@DiscriminatorValue(ProductTypeConstant.SMARTWATCH)
@PrimaryKeyJoinColumn(name = "smartwatch_id")
public class SmartWatchEntity extends ProductEntity {}