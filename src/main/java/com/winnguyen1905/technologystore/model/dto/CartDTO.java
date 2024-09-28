package com.winnguyen1905.technologystore.model.dto;

import java.util.List;

import lombok.*;

@Setter
@Getter
public class CartDTO extends BaseObjectDTO<CartDTO> {
    private UserDTO shop;
    private List<CartItemDTO> cartItems;
    private PriceStatisticsDTO priceStatistic;
}