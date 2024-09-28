package com.winnguyen1905.technologystore.model.dto;

import java.util.List;

import lombok.*;

@Getter
@Setter
public class ProductDTO extends BaseObjectDTO<ProductDTO> {
    private String name;

    private String thumb;

    private String productType;

    private String description;

    private Double price;

    private String slug;

    private ProductAttribute productAttributes;

    private List<VariationDTO> variations;
}