package com.winnguyen1905.technologystore.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.winnguyen1905.technologystore.model.dto.ProductDTO;
import com.winnguyen1905.technologystore.model.request.ProductRequest;
import com.winnguyen1905.technologystore.model.request.ProductSearchRequest;

public interface IProductService {
    ProductDTO handleAddProduct(ProductRequest productRequest, UUID shopId);
    List<ProductDTO> handleUpdateProducts(List<ProductRequest> productRequests, UUID shopId);
    List<ProductDTO> handleChangeProductStatus(List<UUID> ids, UUID shopId);
    ProductDTO handleGetAllProducts(ProductSearchRequest productSearchRequest, Pageable pageable);
    ProductDTO handleGetProduct(UUID id);
}