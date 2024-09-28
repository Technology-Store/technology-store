package com.winnguyen1905.technologystore.model.request;

import com.winnguyen1905.technologystore.model.dto.ProductDTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateProductRequest extends ProductDTO {
    private Boolean isDraft;
    private Boolean isPublished;
}