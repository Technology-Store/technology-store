package com.winnguyen1905.technologystore.util;

import java.util.List;

import com.winnguyen1905.technologystore.entity.CartEntity;
import com.winnguyen1905.technologystore.entity.CartItemEntity;
import com.winnguyen1905.technologystore.exception.CustomRuntimeException;
import com.winnguyen1905.technologystore.model.dto.PriceStatisticsDTO;

public class CartUtils {
    public static PriceStatisticsDTO getPriceStatisticsOfCart(CartEntity cart) {
        // get selected item and check is out of stock ?
        List<CartItemEntity> cartItemsSelected = cart.getCartItems().stream().filter(item -> {
            if (item.getProductVariation().getInventories().stream().allMatch(inven -> inven.getStock() == 0)) 
                throw new CustomRuntimeException("out of stock of product id: " + item.getProductVariation().getProduct().getId());
            return item.getIsSelected();
        }).toList();

        Double totalPriceOfAllProduct = ProductUtils.totalPriceOfAllProduct(cartItemsSelected);

        return PriceStatisticsDTO.builder()
                .amountProductReduced(0.0)
                .amountShipReduced(0.0)
                .finalPrice(totalPriceOfAllProduct)
                .totalPrice(totalPriceOfAllProduct)
                .totalShipPrice(0.0) // Handle after -------------------------------------------------
                .totalDiscountVoucher(0.0).build();
    }
}