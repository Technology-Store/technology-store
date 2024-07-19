package com.winnguyen1905.technologystore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnguyen1905.technologystore.builder.ProductSearchBuilder;
import com.winnguyen1905.technologystore.common.SystemConstant;
import com.winnguyen1905.technologystore.converter.ProductConverter;
import com.winnguyen1905.technologystore.converter.ProductSearchBuilderConverter;
import com.winnguyen1905.technologystore.entity.ProductEntity;
import com.winnguyen1905.technologystore.model.dto.ProductDTO;
import com.winnguyen1905.technologystore.model.request.AddProductRequest;
import com.winnguyen1905.technologystore.model.request.ProductSearchRequest;
import com.winnguyen1905.technologystore.repository.ProductRepository;
import com.winnguyen1905.technologystore.service.IProductService;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private ProductSearchBuilderConverter productSearchBuilderConverter;

    @Override
    public ProductDTO addProduct(AddProductRequest addProductRequest) {
        ProductEntity productEntity = productConverter.modelConverter(addProductRequest, SystemConstant.TO_ENTITY);
        productEntity = productRepository.save(productEntity);
        return productConverter.modelConverter(productEntity, SystemConstant.TO_DTO);
    }

    @Override
    public List<ProductDTO> findAll(ProductSearchRequest productSearchRequest) {
        ProductSearchBuilder productSearchBuilder = productSearchBuilderConverter.toProductSearchBuilder(productSearchRequest);
        List<ProductEntity> products = productRepository.findAll(productSearchBuilder);
        return products.stream().map(item -> (ProductDTO) productConverter.modelConverter(item, SystemConstant.TO_DTO)).toList();
    }

}