package com.winnguyen1905.technologystore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.winnguyen1905.technologystore.exception.CustomRuntimeException;
import com.winnguyen1905.technologystore.model.dto.CheckoutDTO;
import com.winnguyen1905.technologystore.service.ICheckoutService;
import com.winnguyen1905.technologystore.util.SecurityUtils;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("${release.api.prefix}/checkouts")
public class CheckoutController {

    private final ICheckoutService checkoutService;

    @PostMapping("/review")
    public ResponseEntity<CheckoutDTO> checkoutReview(@RequestBody CheckoutDTO checkoutDTO) {
        UUID customerId = SecurityUtils.getCurrentUserId().orElseThrow(() -> new CustomRuntimeException("Not found user"));
        return ResponseEntity.ok().body(this.checkoutService.handleCheckoutReview(checkoutDTO, customerId));
    }
    
    @PostMapping("/order")
    public ResponseEntity<Void> checkoutOrder(@RequestBody CheckoutDTO checkoutDTO) {
        UUID customerId = SecurityUtils.getCurrentUserId().orElseThrow(() -> new CustomRuntimeException("Not found user"));
        this.checkoutService.handleCreateOrder(checkoutDTO, customerId);
        return ResponseEntity.ok().build();
    }

}