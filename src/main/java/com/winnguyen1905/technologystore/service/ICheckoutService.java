package com.winnguyen1905.technologystore.service;

import java.util.UUID;

import com.winnguyen1905.technologystore.model.dto.CheckoutDTO;

public interface ICheckoutService {
    CheckoutDTO handleCheckoutReview(CheckoutDTO checkoutReview, UUID customerId);
    void handleCreateOrder(CheckoutDTO checkout, UUID customerId);
}