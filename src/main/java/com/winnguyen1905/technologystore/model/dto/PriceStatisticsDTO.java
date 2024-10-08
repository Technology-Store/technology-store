package com.winnguyen1905.technologystore.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PriceStatisticsDTO extends AbstractDTO {
    private Double totalPrice;
    private Double totalShipPrice;
    private Double totalDiscountVoucher;

    private Double amountShipReduced;
    private Double amountProductReduced;

    private Double finalPrice;

    private DiscountDTO bestVoucher;
}