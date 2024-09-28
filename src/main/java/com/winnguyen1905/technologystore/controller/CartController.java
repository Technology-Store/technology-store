package com.winnguyen1905.technologystore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.winnguyen1905.technologystore.exception.CustomRuntimeException;
import com.winnguyen1905.technologystore.model.dto.CartDTO;
import com.winnguyen1905.technologystore.model.dto.CartItemDTO;
import com.winnguyen1905.technologystore.model.request.AddToCartRequest;
import com.winnguyen1905.technologystore.service.ICartItemService;
import com.winnguyen1905.technologystore.service.ICartService;
import com.winnguyen1905.technologystore.util.SecurityUtils;
import com.winnguyen1905.technologystore.util.annotation.MetaMessage;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("${release.api.prefix}/carts")
public class CartController {

    private final ICartService cartService;
    private final ICartItemService cartItemService;

    @PostMapping
    @MetaMessage(message = "Add product to cart successfully")
    public ResponseEntity<CartDTO> addToCart(@RequestBody @Valid AddToCartRequest addToCartRequest) {
        UUID customerId = SecurityUtils.getCurrentUserId()
                .orElseThrow(() -> new CustomRuntimeException("Not found user"));
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(this.cartService.handleAddToCart(customerId, addToCartRequest));
    }

    @PatchMapping
    @MetaMessage(message = "Update cart successfully")
    public ResponseEntity<CartItemDTO> updateCartItem(@RequestBody CartItemDTO cartItemDTO) {
        UUID customerId = SecurityUtils.getCurrentUserId()
                .orElseThrow(() -> new CustomRuntimeException("Not found user"));
        return ResponseEntity.ok().body(this.cartItemService.handleUpdateCartItem(cartItemDTO, customerId));
    }

    @GetMapping
    @MetaMessage(message = "Get my card successfully")
    public ResponseEntity<CartDTO> getMethodName(Pageable pageable) {
        UUID customerId = SecurityUtils.getCurrentUserId().orElseThrow(() -> new CustomRuntimeException("Not found user"));
        return ResponseEntity.ok().body(this.cartService.handleGetMyCartDetails(customerId, pageable));
    }

}