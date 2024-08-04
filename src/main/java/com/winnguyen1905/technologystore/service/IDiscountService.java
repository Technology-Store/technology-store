package com.winnguyen1905.technologystore.service;

import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.winnguyen1905.technologystore.model.dto.DiscountDTO;
import com.winnguyen1905.technologystore.model.request.DiscountSearchRequest;

public interface IDiscountService {
    DiscountDTO handleCreateDiscountCode(DiscountDTO discountDTO, String username);
    DiscountDTO handleGetDiscount(UUID id);
    DiscountDTO handleGetAllDiscount(DiscountSearchRequest discountSearchRequest, Pageable pageable);
    Boolean handleVerifyDiscountCode(UUID id);
    void handleDeleteDiscountCode(UUID id);
    void handleCancelDiscountCode(UUID id, String username);
}