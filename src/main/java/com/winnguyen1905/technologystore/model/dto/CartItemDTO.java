package com.winnguyen1905.technologystore.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartItemDTO extends AbstractDTO {
    private ProductDTO product;
    private Integer oldQuantity;
    private Integer quantity;
    private Boolean isSelected;
}