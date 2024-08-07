package com.winnguyen1905.technologystore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.winnguyen1905.technologystore.exception.CustomRuntimeException;
import com.winnguyen1905.technologystore.model.dto.DiscountDTO;
import com.winnguyen1905.technologystore.service.IDiscountService;
import com.winnguyen1905.technologystore.util.SecurityUtils;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("${release.api.prefix}/discounts")
public class DiscountController {

    private final IDiscountService discountService;

    public DiscountController(IDiscountService discountService) {
        this.discountService = discountService;
    }

    // PUBLIC API

    @GetMapping("/{code}/{shop-id}")
    public ResponseEntity<DiscountDTO> getAllDiscountCodeWithProduct(
        Pageable pageable,
        @PathVariable String code, @PathVariable("shop-id") UUID shopId
    ) {
        return ResponseEntity.ok(this.discountService.handleGetAllDiscountCodeWithProducts(code, shopId, pageable));
    }

    @GetMapping("/shop/{shop-id}")
    public ResponseEntity<DiscountDTO> getAllDiscountCodesbyShop(Pageable pageable, @PathVariable("shop-id") UUID shopId) {
        return ResponseEntity.ok(this.discountService.handleGetAllDiscountCodesByShop(shopId, pageable));
    }

    @PostMapping("/apply")
    public ResponseEntity<?> postMethodName(@RequestBody Map<String, UUID> body) {
        UUID cartId = body.get("cart_id");
        UUID discountId = body.get("discount_id");
        return ResponseEntity.ok().body(null);
    }
    
    
    // API FOR SHOPOWNER

    @PostMapping
    public ResponseEntity<DiscountDTO> createDiscountCode(@RequestBody @Valid DiscountDTO discountDTO) {
        UUID shopId = SecurityUtils.getCurrentUserId().orElseThrow(() -> new CustomRuntimeException("Not found username"));
        return ResponseEntity.status(HttpStatus.CREATED.value())
                .body(this.discountService.handleCreateDiscountCode(discountDTO, shopId));
    }
    
}