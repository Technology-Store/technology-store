package com.winnguyen1905.technologystore.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PriceStatisticsDTO extends AbstractDTO<PriceStatisticsDTO> {
    // private CartDTO cart;
    private Double totalPrice;
    private Double totalShipPrice;
    private Double totalDiscountVoucher;

    private Double amountShipReduced;
    private Double amountProductReduced;

    private Double finalPrice;
}