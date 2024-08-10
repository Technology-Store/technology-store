package com.winnguyen1905.technologystore.service;

import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.winnguyen1905.technologystore.model.dto.CartDTO;

public interface ICartService {
    CartDTO handleGetMyCarts(UUID customerId, Pageable pageable);
    CartDTO handleAddCart(CartDTO cartDTO, UUID customerId);
    CartDTO handleGetCartById(UUID cartId, UUID customerId);
}