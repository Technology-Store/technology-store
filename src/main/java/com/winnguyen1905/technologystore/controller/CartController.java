package com.winnguyen1905.technologystore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.winnguyen1905.technologystore.exception.CustomRuntimeException;
import com.winnguyen1905.technologystore.model.dto.CartDTO;
import com.winnguyen1905.technologystore.service.ICartService;
import com.winnguyen1905.technologystore.service.impl.CartService;
import com.winnguyen1905.technologystore.util.SecurityUtils;

import jakarta.validation.Valid;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("${release.api.prefix}/carts")
public class CartController {

    private final ICartService cartService;

    public CartController(ICartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<CartDTO> addCart(@RequestBody @Valid CartDTO cartDTO) {
        UUID customerId = SecurityUtils.getCurrentUserId().orElseThrow(() -> new CustomRuntimeException("Not found username"));
        return ResponseEntity.status(HttpStatus.CREATED.value())
                .body(this.cartService.handleAddCart(cartDTO, customerId));
    }
       
}