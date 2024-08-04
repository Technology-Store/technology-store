package com.winnguyen1905.technologystore.model.request;

import com.winnguyen1905.technologystore.model.dto.ProductDTO;

import lombok.*;

@Getter
@Setter
@Builder
public class ProductRequest extends ProductDTO {
    private String os;

    private Integer ram;

    private Integer rom;

    private Integer pin;

    private String cpu;

    private String gpu;

    private String chipset;

    private Double xSize;

    private Double ySize;

    private String panelType;

    private String resolution;
}