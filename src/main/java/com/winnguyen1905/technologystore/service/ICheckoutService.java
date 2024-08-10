package com.winnguyen1905.technologystore.service;

import java.util.List;
import java.util.UUID;

import com.winnguyen1905.technologystore.model.dto.CartDTO;
import com.winnguyen1905.technologystore.model.dto.CheckoutReviewDTO;

public interface ICheckoutService {
    CheckoutReviewDTO handleCheckoutReview(CheckoutReviewDTO checkoutReview, UUID customerId);
}