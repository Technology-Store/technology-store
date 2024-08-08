package com.winnguyen1905.technologystore.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartItemDTO extends AbstractDTO<CartItemDTO> {
    private ProductDTO product;
    private int quantity;
    private Boolean isSelected;
}