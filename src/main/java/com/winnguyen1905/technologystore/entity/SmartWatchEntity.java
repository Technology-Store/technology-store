package com.winnguyen1905.technologystore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@DiscriminatorValue("SMARTWATCH")
public class SmartWatchEntity extends ProductEntity implements MyProduct {}