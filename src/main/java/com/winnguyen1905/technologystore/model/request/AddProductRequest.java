package com.winnguyen1905.technologystore.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.winnguyen1905.technologystore.model.dto.ProductDTO;

import lombok.*;

@Getter
@Setter
@Builder
public class AddProductRequest extends ProductDTO {
    @JsonProperty("product_type")
    private String productType;

    private String name;

    private String thumb;

    private String description;

    private Double price;

    private String brand;

    private String os;

    private Integer ram;

    private Integer rom;

    private Integer pin;

    private String cpu;

    private String gpu;

    @JsonProperty("x_size")
    private Double xSize;

    @JsonProperty("y_size")
    private Double ySize;

    @JsonProperty("panel_type")
    private String panelType;

    private String resolution;
}