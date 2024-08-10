package com.winnguyen1905.technologystore.model.response;

import com.winnguyen1905.technologystore.model.dto.AbstractDTO;
import com.winnguyen1905.technologystore.model.dto.CartDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApplyDiscountResponse extends AbstractDTO<ApplyDiscountResponse> {
    private CartDTO cart;
    private Double amountReduced;
    private Double totalPrice;
    private Double finalPrice;
}