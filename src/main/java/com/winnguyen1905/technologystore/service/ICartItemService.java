package com.winnguyen1905.technologystore.service;

import java.util.UUID;

import com.winnguyen1905.technologystore.model.dto.CartItemDTO;

public interface ICartItemService {
    CartItemDTO handleUpdateCartItem(CartItemDTO cartItemDTO, UUID customerId);
    void handleDeleteCartItem(CartItemDTO cartItemDTO, UUID customerId);
}