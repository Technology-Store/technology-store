package com.winnguyen1905.technologystore.model.dto;

import java.util.List;
import java.util.Set;

import lombok.*;

@Setter
@Getter
public class CartDTO extends AbstractDTO<CartDTO> {
    private UserDTO shop;
    private List<CartItemDTO> cartItems;
}