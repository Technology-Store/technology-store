package com.winnguyen1905.technologystore.util;

import java.util.List;
import java.util.stream.Collectors;

import com.winnguyen1905.technologystore.entity.CartItemEntity;

public class ProductUtils {
    public static Double totalPriceOfAllProduct(List<CartItemEntity> cartItems) {
        Double totalPrice = cartItems.stream()
            .collect(Collectors.summingDouble(cartItem -> cartItem.getQuantity() * cartItem.getProduct().getPrice()));
        return totalPrice;
    }
}