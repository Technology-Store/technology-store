package com.winnguyen1905.technologystore.model.dto; 

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Getter
@Setter
@SuppressWarnings("rawtypes")
public class ProductDTO extends AbstractDTO {
    
    @JsonProperty("product_type")
    private String productType; 

    private String name;

    private String thumb;

    private String description;

    private Double price;

    private String brand;
    
}