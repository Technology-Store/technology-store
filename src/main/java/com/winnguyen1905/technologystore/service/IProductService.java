package com.winnguyen1905.technologystore.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.winnguyen1905.technologystore.model.dto.ProductDTO;
import com.winnguyen1905.technologystore.model.request.ProductRequest;
import com.winnguyen1905.technologystore.model.request.ProductSearchRequest;

public interface IProductService {
    ProductDTO handleAddProduct(ProductRequest productRequest);
    List<ProductDTO> handleUpdateProducts(List<ProductRequest> productRequests, String shopOwner);
    List<ProductDTO> handleChangeProductStatus(List<UUID> ids, String shopOwner);
    ProductDTO handleGetAllProducts(ProductSearchRequest productSearchRequest, Pageable pageable);
    ProductDTO handleGetProduct(UUID id);
}