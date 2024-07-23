package com.winnguyen1905.technologystore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "smartwatch")
@DiscriminatorValue("smartwatch")
@PrimaryKeyJoinColumn(name = "smartwatch_id")
public class SmartWatchEntity extends ProductEntity {}