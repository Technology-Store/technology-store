package com.winnguyen1905.technologystore.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.winnguyen1905.technologystore.common.ApplyDiscountStatus;
import com.winnguyen1905.technologystore.entity.CartEntity;
import com.winnguyen1905.technologystore.entity.CartItemEntity;
import com.winnguyen1905.technologystore.entity.OrderEntity;
import com.winnguyen1905.technologystore.entity.OrderItemEntity;
import com.winnguyen1905.technologystore.exception.CustomRuntimeException;
import com.winnguyen1905.technologystore.model.dto.CheckoutDTO;
import com.winnguyen1905.technologystore.model.dto.PriceStatisticsDTO;
import com.winnguyen1905.technologystore.repository.CartItemRepository;
import com.winnguyen1905.technologystore.repository.CartRepository;
import com.winnguyen1905.technologystore.service.ICartService;
import com.winnguyen1905.technologystore.service.ICheckoutService;
import com.winnguyen1905.technologystore.service.IDiscountService;
import com.winnguyen1905.technologystore.util.AccumulateUtils;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CheckoutService implements ICheckoutService {

    private final IDiscountService discountService;
    private final ICartService cartService;
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;

    @Override
    public CheckoutDTO handleCheckoutReview(CheckoutDTO checkoutReview, UUID customerId) {
        PriceStatisticsDTO priceStatisticsDTO = PriceStatisticsDTO.builder().build();
        for (CheckoutDTO.CheckoutItemDTO checkoutItem : checkoutReview.getCheckoutItemDTOs()) {
            PriceStatisticsDTO statisticsItem = checkoutItem.getDiscounts().stream()
                    .map(discountDTO -> this.discountService.handleApplyDiscountCode(discountDTO, customerId, ApplyDiscountStatus.REVIEW))
                    .min(Comparator.comparing(PriceStatisticsDTO::getFinalPrice))
                    .orElse(null);
            if(statisticsItem == null) statisticsItem = this.cartService.handleGetPriceStatisticsOfCart(checkoutItem.getCartDTO(), customerId);
            priceStatisticsDTO = AccumulateUtils.accumulate(statisticsItem, priceStatisticsDTO);

            checkoutItem.setPriceStatisticsDTO(statisticsItem);
            checkoutItem.setBestVoucher(null);
        }
        return checkoutReview;
    }

    @Override
    @Transactional
    public void handleCreateOrder(CheckoutDTO checkout, UUID customerId) {

        for(CheckoutDTO.CheckoutItemDTO checkoutItem : checkout.getCheckoutItemDTOs()) {

            // Get statistic price for each shop products
            PriceStatisticsDTO statisticsItem = checkoutItem.getDiscounts().stream()
                    .map(discountDTO -> this.discountService.handleApplyDiscountCode(discountDTO, customerId, ApplyDiscountStatus.COMMIT))
                    .min(Comparator.comparing(PriceStatisticsDTO::getFinalPrice))
                    .orElse(null);
            if(statisticsItem == null) statisticsItem = this.cartService.handleGetPriceStatisticsOfCart(checkoutItem.getCartDTO(), customerId);

            // Get order items
            CartEntity cart = this.cartRepository.findByIdAndCustomerId(checkoutItem.getCartDTO().getId(), customerId).orElseThrow(() -> new CustomRuntimeException("Not found cart"));
            List<CartItemEntity> cartItemsSelected = cart.getCartItems().stream().filter(item -> item.getIsSelected()).toList();
            List<OrderItemEntity> orderItems = cartItemsSelected.stream().map(cartItem -> this.modelMapper.map(cartItem, OrderItemEntity.class)).toList();

            // Set property
            OrderEntity order = new OrderEntity();
            order.setCustomer(cart.getCustomer());
            order.setShop(cart.getShop());
            order.setOrderItems(orderItems);
            
            checkoutItem.setPriceStatisticsDTO(statisticsItem);
        }
    }

}