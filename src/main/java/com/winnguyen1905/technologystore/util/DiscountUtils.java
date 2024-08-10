package com.winnguyen1905.technologystore.util;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import com.winnguyen1905.technologystore.common.ApplyDiscountType;
import com.winnguyen1905.technologystore.entity.CartItemEntity;
import com.winnguyen1905.technologystore.entity.DiscountEntity;
import com.winnguyen1905.technologystore.entity.UserEntity;
import com.winnguyen1905.technologystore.exception.CustomRuntimeException;

public class DiscountUtils {

    public static Boolean isUsable(DiscountEntity discount, UserEntity customer) {
        long timeUsed = discount.getCustomer().stream().filter(item -> item.equals(customer)).count();
        if(discount.getMaxUsesPerUser() <= timeUsed) throw new CustomRuntimeException("Use count reach to maximum");
        if(discount.getEndDate().isBefore(Instant.now())) throw new CustomRuntimeException("Discount has been expired");
        if(discount.getUsesCount() >= discount.getMaxUses()) throw new CustomRuntimeException("Discount be used maximum");
        return true;
    }

    public static Double totalPriceOfAllProductInDiscountProgram(DiscountEntity discount, List<CartItemEntity> cartItemsSelected) {
        return cartItemsSelected.stream()
            .collect(Collectors.summingDouble(
                cartItem ->
                    discount.getProducts().contains(cartItem.getProduct()) || discount.getAppliesTo().equals(ApplyDiscountType.ALL) 
                        ? cartItem.getQuantity() * cartItem.getProduct().getPrice() 
                        : 0.0)
            );
    }
}