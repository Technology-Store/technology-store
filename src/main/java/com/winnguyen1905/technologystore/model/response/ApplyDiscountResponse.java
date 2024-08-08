package com.winnguyen1905.technologystore.model.response;

import com.winnguyen1905.technologystore.model.dto.AbstractDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApplyDiscountResponse extends AbstractDTO<ApplyDiscountResponse> {
    private double totalPrice;
    private double finalPrice;
}