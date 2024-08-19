package com.winnguyen1905.technologystore.service.impl;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.winnguyen1905.technologystore.entity.CartItemEntity;
import com.winnguyen1905.technologystore.entity.UserEntity;
import com.winnguyen1905.technologystore.exception.CustomRuntimeException;
import com.winnguyen1905.technologystore.model.dto.CartItemDTO;
import com.winnguyen1905.technologystore.repository.CartItemRepository;
import com.winnguyen1905.technologystore.repository.UserRepository;
import com.winnguyen1905.technologystore.service.ICartItemService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartItemService implements ICartItemService {

    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public CartItemDTO handleUpdateCartItem(CartItemDTO cartItemDTO, UUID customerId) {
        // Valid
        CartItemEntity cartItem = this.cartItemRepository.findById(cartItemDTO.getId())
                .orElseThrow(() -> new CustomRuntimeException("Not found cart-item id " + cartItemDTO.getId()));
        UserEntity customer = this.userRepository.findById(customerId)
                .orElseThrow(() -> new CustomRuntimeException("Not found user id " + customerId));
        if (!cartItem.getCart().getCustomer().equals(customer))
            throw new CustomRuntimeException("Not found cart-item please check your information");

        // Handle data changed
        if (cartItemDTO.getQuantity() != null && cartItemDTO.getQuantity() == 0) {
            handleDeleteCartItem(cartItemDTO, customerId);
            return null;
        }
        if (cartItemDTO.getQuantity() != null && cartItemDTO.getQuantity() > 0)
            cartItem.setQuantity(cartItemDTO.getQuantity());
        if (cartItemDTO.getIsSelected() != null)
            cartItem.setIsSelected(cartItemDTO.getIsSelected());

        // Sending response
        cartItem = this.cartItemRepository.save(cartItem);
        return this.modelMapper.map(cartItem, CartItemDTO.class);
    }

    @Override
    public void handleDeleteCartItem(CartItemDTO cartItemDTO, UUID customerId) {
        // Valid
        CartItemEntity cartItem = this.cartItemRepository.findById(cartItemDTO.getId())
                .orElseThrow(() -> new CustomRuntimeException("Not found cart-item id " + cartItemDTO.getId()));
        UserEntity customer = this.userRepository.findById(customerId)
                .orElseThrow(() -> new CustomRuntimeException("Not found user id " + customerId));
        if (!cartItem.getCart().getCustomer().equals(customer))
            throw new CustomRuntimeException("Not found cart-item please check your information");

        this.cartItemRepository.delete(cartItem);
    }

}