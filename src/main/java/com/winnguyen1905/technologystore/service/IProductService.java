package com.winnguyen1905.technologystore.service;

import java.util.List;

import com.winnguyen1905.technologystore.model.dto.ProductDTO;
import com.winnguyen1905.technologystore.model.request.AddProductRequest;
import com.winnguyen1905.technologystore.model.request.ProductSearchRequest;

public interface IProductService {
    ProductDTO addProduct(AddProductRequest addProductRequest);
    List<ProductDTO> findAll(ProductSearchRequest productSearchRequest);
}