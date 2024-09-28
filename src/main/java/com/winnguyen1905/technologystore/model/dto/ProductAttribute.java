package com.winnguyen1905.technologystore.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductAttribute extends BaseObjectDTO {
    private String brand;
    private String size;
    private String material;

    private String manuFacturer;
    private String model;
    private String color;
}