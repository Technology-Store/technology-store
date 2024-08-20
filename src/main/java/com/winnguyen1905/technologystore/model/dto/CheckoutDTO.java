package com.winnguyen1905.technologystore.model.dto;

import java.util.List;
import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CheckoutDTO extends AbstractDTO<CheckoutDTO> {

    // request
    private List<CheckoutItemDTO> checkoutItems;
    // response
    private PriceStatisticsDTO PriceStatistics;

    @Getter
    @Setter
    @Builder
    public static class CheckoutItemDTO extends AbstractDTO<CheckoutItemDTO> {
        private CartDTO cart;
        private Set<DiscountDTO> discounts;
        private DiscountDTO bestVoucher;    

        private PriceStatisticsDTO PriceStatistics;
    }

}