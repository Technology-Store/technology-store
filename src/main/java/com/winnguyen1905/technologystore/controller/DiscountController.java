package com.winnguyen1905.technologystore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.winnguyen1905.technologystore.exception.CustomRuntimeException;
import com.winnguyen1905.technologystore.model.dto.DiscountDTO;
import com.winnguyen1905.technologystore.service.IDiscountService;
import com.winnguyen1905.technologystore.util.SecurityUtils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("${release.api.prefix}/discounts")
public class DiscountController {

    private final IDiscountService discountService;

    public DiscountController(IDiscountService discountService) {
        this.discountService = discountService;}

    @PostMapping
    public ResponseEntity<DiscountDTO> createDiscountCode(@RequestBody DiscountDTO discountDTO) {
        String username = SecurityUtils.getCurrentUserLogin()
                .orElseThrow(() -> new CustomRuntimeException("Not found username"));
        return ResponseEntity.status(HttpStatus.CREATED.value())
                .body(this.discountService.handleCreateDiscountCode(discountDTO, username));
    } 
}