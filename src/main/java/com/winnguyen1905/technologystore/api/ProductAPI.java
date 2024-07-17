package com.winnguyen1905.technologystore.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.winnguyen1905.technologystore.model.dto.ProductDTO;
import com.winnguyen1905.technologystore.model.request.AddProductRequest;
import com.winnguyen1905.technologystore.service.IProductService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/v1/products")
public class ProductAPI {

    @Autowired
    private IProductService productService;

    @PostMapping("/")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody AddProductRequest addProductRequest) {
        return ResponseEntity.ok(productService.addProduct(addProductRequest));
    }

    @GetMapping("/")
    public ResponseEntity<?> findAllProduct(
        @RequestParam(required = false) Map<String, Object> params,
        @RequestParam(name = "typeCode", required = false) List<String> typeCode
    ) {
        return ResponseEntity.ok(productService.findAll());
    }
    
}