package com.winnguyen1905.technologystore.service;

import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.winnguyen1905.technologystore.model.dto.CartDTO;
import com.winnguyen1905.technologystore.model.dto.PriceStatisticsDTO;
import com.winnguyen1905.technologystore.model.request.AddToCartRequest;
import com.winnguyen1905.technologystore.model.response.PaginationResponse;

public interface ICartService {
    PaginationResponse<CartDTO> handleGetCarts(UUID customerId, Pageable pageable);
    void handleAddToCart(UUID customerId, AddToCartRequest addToCartRequest);
    CartDTO handleGetCartById(UUID cartId, UUID customerId);
    Boolean handleValidateCart(CartDTO cartDTO, UUID customerId);
    PriceStatisticsDTO handleGetPriceStatisticsOfCart(UUID customerId, UUID cartId);
}