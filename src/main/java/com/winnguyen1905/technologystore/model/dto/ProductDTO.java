package com.winnguyen1905.technologystore.model.dto;

import lombok.*;

@Getter
@Setter
public class ProductDTO extends AbstractDTO<ProductDTO> {
    private String productType;

    private String name;

    private String thumb;

    private String description;

    private Double price;

    private String brand;

    private String slug;

    private Boolean isDraft;
    
    private Boolean isPublished;
}