package com.winnguyen1905.technologystore.controller;

import com.winnguyen1905.technologystore.common.SystemConstant;
import com.winnguyen1905.technologystore.model.dto.ProductDTO;
import com.winnguyen1905.technologystore.model.request.AddProductRequest;
import com.winnguyen1905.technologystore.model.request.ProductSearchRequest;
import com.winnguyen1905.technologystore.service.IProductService; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @PostMapping("/")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody AddProductRequest addProductRequest) {
        return ResponseEntity.created(null).body(this.productService.handleAddProduct(addProductRequest));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllProducts(Pageable pageable,
        @ModelAttribute(SystemConstant.MODEL) ProductSearchRequest productSearchRequest
    ) {
        return ResponseEntity.ok(this.productService.handleGetAllProducts(productSearchRequest, pageable));
    }

    @GetMapping("/draft")
    public ResponseEntity<ProductDTO> getDraftProducts(Pageable pageable,
        @ModelAttribute(SystemConstant.MODEL) ProductSearchRequest productSearchRequest
    ) {
        return ResponseEntity.ok(this.productService.handleGetDraftProducts(productSearchRequest, pageable));
    }
}