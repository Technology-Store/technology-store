package com.winnguyen1905.technologystore.repository.custom;

import java.util.List;

import com.winnguyen1905.technologystore.entity.ProductEntity;
import com.winnguyen1905.technologystore.model.request.SearchProductRequest;

public interface ProductRepositoryCustom {
    List<ProductEntity> findAll(SearchProductRequest productSearchRequest);
}