package com.winnguyen1905.technologystore.util;

import java.time.Instant;
import java.util.List;
import java.util.Set;

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
        Double totalPriceOfAllProductInDiscountProgram = 0.0;
        if(discount.getAppliesTo().equals(ApplyDiscountType.SPECIFIC)) {
            for (CartItemEntity cartItem : cartItemsSelected) {
                if(discount.getProducts().contains(cartItem.getProduct()))
                    totalPriceOfAllProductInDiscountProgram += cartItem.getQuantity() * cartItem.getProduct().getPrice();
            }
        } else for (CartItemEntity cartItem : cartItemsSelected)
                totalPriceOfAllProductInDiscountProgram += cartItem.getQuantity() * cartItem.getProduct().getPrice();
        return totalPriceOfAllProductInDiscountProgram;
    }
}