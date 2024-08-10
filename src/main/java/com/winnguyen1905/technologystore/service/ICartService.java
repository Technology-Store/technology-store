package com.winnguyen1905.technologystore.service;

import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.winnguyen1905.technologystore.model.dto.CartDTO;
import com.winnguyen1905.technologystore.model.dto.PriceStatisticsDTO;

public interface ICartService {
    CartDTO handleGetMyCarts(UUID customerId, Pageable pageable);
    CartDTO handleAddCart(CartDTO cartDTO, UUID customerId);
    CartDTO handleGetCartById(UUID cartId, UUID customerId);
    PriceStatisticsDTO handleGetPriceStatisticsOfCart(CartDTO cartDTO, UUID customerId);
}