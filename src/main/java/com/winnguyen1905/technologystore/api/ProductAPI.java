package com.winnguyen1905.technologystore.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/products")
public class ProductAPI {
    @PostMapping("/")
    public String postMethodName(@RequestBody String entity) {        
        return entity;
    }
    
}