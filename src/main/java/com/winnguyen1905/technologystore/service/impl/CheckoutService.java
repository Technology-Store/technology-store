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
import com.winnguyen1905.technologystore.entity.InventoryEntity;
import com.winnguyen1905.technologystore.entity.OrderEntity;
import com.winnguyen1905.technologystore.entity.OrderItemEntity;
import com.winnguyen1905.technologystore.exception.CustomRuntimeException;
import com.winnguyen1905.technologystore.model.dto.CheckoutDTO;
import com.winnguyen1905.technologystore.model.dto.PriceStatisticsDTO;
import com.winnguyen1905.technologystore.repository.CartItemRepository;
import com.winnguyen1905.technologystore.repository.CartRepository;
import com.winnguyen1905.technologystore.repository.OrderRepository;
import com.winnguyen1905.technologystore.service.ICartService;
import com.winnguyen1905.technologystore.service.ICheckoutService;
import com.winnguyen1905.technologystore.service.IDiscountService;
import com.winnguyen1905.technologystore.service.IInventoryService;
import com.winnguyen1905.technologystore.util.AccumulateUtils;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CheckoutService implements ICheckoutService {

    private final IDiscountService discountService;
    private final ICartService cartService;
    private final IInventoryService inventoryService;
    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;

    @Override
    public CheckoutDTO handleCheckoutReview(CheckoutDTO checkoutReview, UUID customerId) {
        PriceStatisticsDTO priceStatisticsDTO = PriceStatisticsDTO.builder().build();
        for (CheckoutDTO.CheckoutItemDTO checkoutItem : checkoutReview.getCheckoutItems()) {
            PriceStatisticsDTO statisticsItem = checkoutItem.getDiscounts().stream()
                    .map(discountDTO -> this.discountService.handleApplyDiscountCode(discountDTO, customerId, ApplyDiscountStatus.REVIEW))
                    .min(Comparator.comparing(PriceStatisticsDTO::getFinalPrice))
                    .orElse(null);
            if(statisticsItem == null) statisticsItem = this.cartService.handleGetPriceStatisticsOfCart(checkoutItem.getCart(), customerId);
            priceStatisticsDTO = AccumulateUtils.accumulate(statisticsItem, priceStatisticsDTO);

            checkoutItem.setPriceStatistics(statisticsItem);
            checkoutItem.setBestVoucher(statisticsItem.getBestVoucher());
        }
        return checkoutReview;
    }

    @Override
    @Transactional
    public void handleCreateOrder(CheckoutDTO checkout, UUID customerId) {

        for(CheckoutDTO.CheckoutItemDTO checkoutItem : checkout.getCheckoutItems()) {
            // Get statistic price for each shop products
            PriceStatisticsDTO statisticsItem = checkoutItem.getDiscounts().stream()
                    .map(discountDTO -> this.discountService.handleApplyDiscountCode(discountDTO, customerId, ApplyDiscountStatus.REVIEW))
                    .min(Comparator.comparing(PriceStatisticsDTO::getFinalPrice))
                    .orElse(null);
            
            if(statisticsItem != null) statisticsItem = this.discountService.handleApplyDiscountCode(statisticsItem.getBestVoucher(), customerId, ApplyDiscountStatus.COMMIT);
            else if(statisticsItem == null) statisticsItem = this.cartService.handleGetPriceStatisticsOfCart(checkoutItem.getCart(), customerId);

            // Get order items
            CartEntity cart = this.cartRepository.findByIdAndCustomerId(checkoutItem.getCart().getId(), customerId).orElseThrow(() -> new CustomRuntimeException("Not found cart"));
            List<CartItemEntity> cartItemsSelected = cart.getCartItems().stream().filter(item -> item.getIsSelected()).toList();
            List<OrderItemEntity> orderItems = cartItemsSelected.stream().map(cartItem -> this.modelMapper.map(cartItem, OrderItemEntity.class)).toList();

            // Is suffering stock
            orderItems.stream().forEach(item -> {
                List<InventoryEntity> inventories = item.getProduct().getInventories().stream().filter(inven -> inven.getStock() > 0).toList();
                if(
                    inventories.isEmpty()
                    || !this.inventoryService.handleUpdateInventoryForReservation(inventories.get(0).getId(), customerId, item.getQuantity())
                ) throw new CustomRuntimeException("Cannot order product id " + item.getProduct().getId());
            });

            // Set property
            OrderEntity order = this.modelMapper.map(statisticsItem, OrderEntity.class);
            order.setCustomer(cart.getCustomer());
            order.setShop(cart.getShop());
            order.setOrderItems(orderItems);

            // Remove cart item selected
            cart.getCartItems().removeAll(cartItemsSelected);

            // Save data
            this.cartRepository.save(cart);
            this.orderRepository.save(order);
        }
    }

}