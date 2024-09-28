package com.winnguyen1905.technologystore.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.winnguyen1905.technologystore.model.dto.ProductDTO;
import com.winnguyen1905.technologystore.model.request.AddProductRequest;
import com.winnguyen1905.technologystore.model.request.SearchProductRequest;
import com.winnguyen1905.technologystore.model.response.PaginationResponse;

public interface IProductService {
    ProductDTO handleAddProduct(AddProductRequest productRequest, UUID shopId);

    List<ProductDTO> handleUpdateManyProducts(List<AddProductRequest> productRequests, UUID shopId);

    List<ProductDTO> handleChangeProductStatus(List<UUID> ids, UUID shopId);

    PaginationResponse<ProductDTO> handleGetAllProducts(SearchProductRequest productSearchRequest, Pageable pageable);

    ProductDTO handleGetProduct(UUID id);

    void handleDeleteProducts(List<UUID> ids, UUID shopId);
}