package com.winnguyen1905.technologystore.service;

import java.util.List;

import com.winnguyen1905.technologystore.model.dto.ProductDTO;
import com.winnguyen1905.technologystore.model.request.AddProductRequest;

public interface IProductService {
    ProductDTO addProduct(AddProductRequest pAddProductRequest);
    List<ProductDTO> findAll();
}