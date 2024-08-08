package com.winnguyen1905.technologystore.model.request;

import com.winnguyen1905.technologystore.model.dto.ProductDTO;

import lombok.*;

@Getter
@Setter
public class ProductRequest extends ProductDTO {
    private String os;

    private int ram;

    private int rom;

    private int pin;

    private String cpu;

    private String gpu;

    private String chipset;

    private double xSize;

    private double ySize;

    private String panelType;

    private String resolution;
}