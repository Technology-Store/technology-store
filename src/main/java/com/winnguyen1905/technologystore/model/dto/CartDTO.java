package com.winnguyen1905.technologystore.model.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
public class CartDTO extends AbstractDTO<CartDTO> {
    private UserDTO shop;
    private List<CartItemDTO> cartItems;
}