package com.winnguyen1905.technologystore.service;

import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.winnguyen1905.technologystore.model.dto.DiscountDTO;

public interface IDiscountService {
    DiscountDTO handleCreateDiscountCode(DiscountDTO discountDTO, UUID shopId);

    DiscountDTO handleGetDiscount(UUID id);

    DiscountDTO handleGetAllDiscountCodesByShop(UUID shopId, Pageable pageable);

    DiscountDTO handleGetAllDiscountCodeWithProducts(String code, UUID shopId, Pageable pageable);

    Number handleApplyDiscountForCart(UUID discountId, UUID customerId);

    Boolean handleVerifyDiscountCode(UUID id);

    void handleDeleteDiscountCode(UUID id);

    void handleCancelDiscountCode(UUID id, String username);
}