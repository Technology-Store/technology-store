package com.winnguyen1905.technologystore.service.impl;

import java.util.Comparator;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.winnguyen1905.technologystore.common.ApplyDiscountStatus;
import com.winnguyen1905.technologystore.model.dto.CheckoutReviewDTO;
import com.winnguyen1905.technologystore.model.dto.PriceStatisticsDTO;
import com.winnguyen1905.technologystore.service.ICartService;
import com.winnguyen1905.technologystore.service.ICheckoutService;
import com.winnguyen1905.technologystore.service.IDiscountService;
import com.winnguyen1905.technologystore.util.AccumulateUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CheckoutService implements ICheckoutService {

    private final IDiscountService discountService;
    private final ICartService cartService;

    @Override
    public CheckoutReviewDTO handleCheckoutReview(CheckoutReviewDTO checkoutReview, UUID customerId) {
        PriceStatisticsDTO priceStatisticsDTO = PriceStatisticsDTO.builder().build();
        for (CheckoutReviewDTO.CheckoutItemDTO checkoutItem : checkoutReview.getCheckoutItemDTOs()) {
            PriceStatisticsDTO statisticsItem = checkoutItem.getDiscounts().stream()
                    .map(discountDTO -> this.discountService.handleApplyDiscountCode(discountDTO, customerId, ApplyDiscountStatus.REVIEW))
                    .min(Comparator.comparing(PriceStatisticsDTO::getFinalPrice))
                    .orElse(null);
                    
            if(statisticsItem == null) statisticsItem = cartService.handleGetPriceStatisticsOfCart(checkoutItem.getCartDTO(), customerId);
            priceStatisticsDTO = AccumulateUtils.accumulate(statisticsItem, priceStatisticsDTO);

            checkoutItem.setPriceStatisticsDTO(statisticsItem);
            checkoutItem.setBestVoucher(null);
        }
        return checkoutReview;
    }

}