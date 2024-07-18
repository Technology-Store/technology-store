package com.winnguyen1905.technologystore.repository.custom;

import java.util.List;

import com.winnguyen1905.technologystore.builder.ProductSearchBuilder;
import com.winnguyen1905.technologystore.entity.ProductEntity;

public interface ProductRepositoryCustom {
    List<ProductEntity> findAll(ProductSearchBuilder productSearchBuilder);
}