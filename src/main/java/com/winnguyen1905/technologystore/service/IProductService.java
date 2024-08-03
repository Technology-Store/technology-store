package com.winnguyen1905.technologystore.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.winnguyen1905.technologystore.model.dto.ProductDTO;
import com.winnguyen1905.technologystore.model.request.AddProductRequest;
import com.winnguyen1905.technologystore.model.request.ProductSearchRequest;

public interface IProductService {
    ProductDTO handleAddProduct(AddProductRequest addProductRequest);
    List<ProductDTO> handleGetAllProducts(ProductSearchRequest productSearchRequest, org.springframework.data.domain.Pageable pageable);
    ProductDTO handleGetDraftProducts(ProductSearchRequest productSearchRequest, Pageable pageable);
}