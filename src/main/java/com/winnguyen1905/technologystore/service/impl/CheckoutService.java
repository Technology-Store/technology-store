package com.winnguyen1905.technologystore.service.impl;

import java.util.UUID;

import com.winnguyen1905.technologystore.model.dto.CheckoutReviewDTO;
import com.winnguyen1905.technologystore.model.dto.PriceStatisticsDTO;
import com.winnguyen1905.technologystore.service.ICheckoutService;

public class CheckoutService implements ICheckoutService {

    @Override
    public CheckoutReviewDTO handleCheckoutReview(CheckoutReviewDTO checkoutReview, UUID customerId) {
        PriceStatisticsDTO priceStatisticsDTO = PriceStatisticsDTO.builder().build();
        for (CheckoutReviewDTO.CheckoutItemDTO checkoutItem : checkoutReview.getCheckoutItemDTOs()) {
            PriceStatisticsDTO statisticsItem = PriceStatisticsDTO.builder().build();
            
        }
        return null;
    }

}