package com.winnguyen1905.technologystore.model.dto;

import java.util.List;
import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CheckoutReviewDTO extends AbstractDTO<CheckoutReviewDTO> {
    private List<CartDTO> cartDTOs;
    // global discount
    private Double totalPrice;
    private Double totalShipPrice;
    private Double totalDiscountVoucher;
    private Double finalPrice;
}