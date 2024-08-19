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

    private List<CheckoutItemDTO> checkoutItemDTOs;
    
    private PriceStatisticsDTO PriceStatisticsDTO;

    @Getter
    @Setter
    @Builder
    public static class CheckoutItemDTO {
        private CartDTO cartDTO;
        private Set<DiscountDTO> discounts;
        private DiscountDTO bestVoucher;    

        private PriceStatisticsDTO PriceStatisticsDTO;
    }
    
}