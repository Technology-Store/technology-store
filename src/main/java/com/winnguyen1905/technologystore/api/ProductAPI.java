package com.winnguyen1905.technologystore.api;

import com.winnguyen1905.technologystore.common.SystemConstant;
import com.winnguyen1905.technologystore.model.dto.ProductDTO;
import com.winnguyen1905.technologystore.model.request.AddProductRequest;
import com.winnguyen1905.technologystore.model.request.ProductSearchRequest;
import com.winnguyen1905.technologystore.service.IProductService; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductAPI {

    @Autowired
    private IProductService productService;

    @PostMapping("/")
    public ResponseEntity<ProductDTO> addProduct(
        @RequestBody AddProductRequest addProductRequest
    ) {
        return ResponseEntity.ok(productService.addProduct(addProductRequest));
    }

    @GetMapping("/")
    public ResponseEntity<?> findAllProduct(
        @ModelAttribute(SystemConstant.MODEL) ProductSearchRequest productSearchRequest
    ) {
        return ResponseEntity.ok(productService.findAll(productSearchRequest));
    }

}