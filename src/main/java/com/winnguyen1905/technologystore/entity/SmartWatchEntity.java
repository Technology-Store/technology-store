package com.winnguyen1905.technologystore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "smartwatch")
@DiscriminatorValue("smartwatch")
public class SmartWatchEntity extends ProductEntity implements MyProduct {}