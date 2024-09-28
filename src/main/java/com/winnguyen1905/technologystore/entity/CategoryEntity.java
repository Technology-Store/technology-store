package com.winnguyen1905.technologystore.entity;

import com.winnguyen1905.technologystore.entity.base.BaseEntityAudit;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntityAudit {
    
}