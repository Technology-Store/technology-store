package com.winnguyen1905.technologystore.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
import com.winnguyen1905.technologystore.util.NormalSpecificationUtils;
import com.winnguyen1905.technologystore.util.SecurityUtils;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private ProductSearchBuilderConverter productSearchBuilderConverter;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDTO handleAddProduct(AddProductRequest addProductRequest) {
        ProductEntity product = productConverter.modelConverter(addProductRequest, SystemConstant.TO_ENTITY);
        product = this.productRepository.save(product);
        return productConverter.modelConverter(product, SystemConstant.TO_DTO);
    }

    @Override
    public List<ProductDTO> handleGetAllProducts(ProductSearchRequest productSearchRequest, Pageable pageable) {
        ProductSearchBuilder productSearchBuilder = productSearchBuilderConverter.toProductSearchBuilder(productSearchRequest);
        List<ProductEntity> products = productRepository.findAll(productSearchBuilder);
        return products.stream().map(item -> (ProductDTO) productConverter.modelConverter(item, SystemConstant.TO_DTO)).toList();
    }

    @Override
    public ProductDTO handleGetDraftProducts(ProductSearchRequest productSearchRequest, Pageable pageable) {
        if(productSearchRequest.getIsDraft() == null) productSearchRequest.setIsDraft(true); // "TESTING"
        List<Specification<ProductEntity>> specList = NormalSpecificationUtils.toNormalSpec(productSearchRequest);
        Page<ProductEntity> productPage = this.productRepository.findAll(Specification.allOf(specList), pageable);
        return this.modelMapper.map(productPage, ProductDTO.class);
    }
}