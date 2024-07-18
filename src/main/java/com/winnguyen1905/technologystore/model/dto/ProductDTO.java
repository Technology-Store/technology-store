package com.winnguyen1905.technologystore.model.dto; 

import lombok.*;

@Getter
@Setter
@SuppressWarnings("rawtypes")
public class ProductDTO extends AbstractDTO {
    
    private String productType;

    private String name;

    private String thumb;

    private String description;

    private Double price;

    private String brand;
    
}