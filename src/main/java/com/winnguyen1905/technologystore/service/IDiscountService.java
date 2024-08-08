package com.winnguyen1905.technologystore.service;

import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.winnguyen1905.technologystore.model.dto.DiscountDTO;
import com.winnguyen1905.technologystore.model.response.ApplyDiscountResponse;

public interface IDiscountService {
    DiscountDTO handleCreateDiscountCode(DiscountDTO discountDTO, UUID shopId);

    DiscountDTO handleGetDiscount(UUID id);

    DiscountDTO handleGetAllDiscountCodesByShop(UUID shopId, Pageable pageable);

    DiscountDTO handleGetAllDiscountCodeWithProducts(String code, UUID shopId, Pageable pageable);

    ApplyDiscountResponse handleApplyDiscountForCart(DiscountDTO discountDTO, UUID customerId);

    Boolean handleVerifyDiscountCode(UUID id);

    void handleDeleteDiscountCode(UUID id);

    void handleCancelDiscountCode(UUID id, String username);

    void handleCancelDiscountForCart(DiscountDTO discountDTO, UUID customerId);
}