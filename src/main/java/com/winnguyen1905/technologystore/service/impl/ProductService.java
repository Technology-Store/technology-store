package com.winnguyen1905.technologystore.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winnguyen1905.technologystore.common.SystemConstant;
import com.winnguyen1905.technologystore.converter.ProductConverter;
import com.winnguyen1905.technologystore.entity.ProductEntity;
import com.winnguyen1905.technologystore.exception.CustomRuntimeException;
import com.winnguyen1905.technologystore.model.dto.ProductDTO;
import com.winnguyen1905.technologystore.model.request.ProductRequest;
import com.winnguyen1905.technologystore.model.request.ProductSearchRequest;
import com.winnguyen1905.technologystore.repository.InventoryRepository;
import com.winnguyen1905.technologystore.repository.ProductRepository;
import com.winnguyen1905.technologystore.service.IProductService;
import com.winnguyen1905.technologystore.util.NormalSpecificationUtils;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;
    private final ModelMapper modelMapper;
    private final InventoryRepository inventoryRepository;

    public ProductService(InventoryRepository inventoryRepository, ProductRepository productRepository,
            ProductConverter productConverter, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
        this.modelMapper = modelMapper;
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public ProductDTO handleAddProduct(ProductRequest productRequest) {
        ProductEntity product = productConverter.toProductEntity(productRequest);
        product = this.productRepository.save(product);
        return this.productConverter.toProductDTO(product);
    }

    @Override
    public List<ProductDTO> handleUpdateProducts(List<ProductRequest> productRequests, String shopOwner) {
        List<UUID> ids = productRequests.stream().map(item -> item.getId()).toList();
        List<ProductEntity> products = this.productRepository.findByIdInAndCreatedByAndOrderByIdAsc(ids, shopOwner);
        if (products.size() != ids.size())
            throw new CustomRuntimeException(
                    "Cannot update because " + products.size() + " of " + ids.size() + " product be found");

        List<ProductEntity> newDataOfProducts = productRequests.stream()
                .map(item -> (ProductEntity) this.productConverter.toProductEntity(item))
                .sorted(Comparator.comparing(ProductEntity::getId)).collect(Collectors.toList());
        for (int i = 0; i < products.size(); i++) {
            ProductEntity oldData = products.get(i), newData = newDataOfProducts.get(i);
            this.modelMapper.map(newData, oldData);
            products.set(i, oldData);
        }
        return products.stream().map(item -> (ProductDTO) this.productConverter.toProductDTO(item)).toList();
    }

    @Override
    public ProductDTO handleGetAllProducts(ProductSearchRequest productSearchRequest, Pageable pageable) {
        List<Specification<ProductEntity>> specList = NormalSpecificationUtils.toNormalSpec(productSearchRequest);
        Page<ProductEntity> productPages = this.productRepository.findAll(Specification.allOf(specList), pageable);
        return this.modelMapper.map(productPages, ProductDTO.class);
    }

    @Override
    public List<ProductDTO> handleChangeProductStatus(List<UUID> ids, String shopOwner) {
        List<ProductEntity> products = this.productRepository.findByIdInAndCreatedBy(ids, shopOwner);
        if (products.size() != ids.size())
            throw new CustomRuntimeException(
                    "Cannot update because " + products.size() + " of " + ids.size() + " product be found");
        products = products.stream().map(item -> {
            item.setIsDraft(!item.getIsDraft());
            item.setIsPublished(!item.getIsPublished());
            return item;
        }).toList();
        products = this.productRepository.saveAll(products);
        return products.stream().map(item -> (ProductDTO) this.productConverter.toProductDTO(item)).toList();
    }

    @Override
    public ProductDTO handleGetProduct(UUID id) {
        ProductEntity product = this.productRepository.findById(id)
                .orElseThrow(() -> new CustomRuntimeException("Not found product id " + id.toString()));
        if (!product.getIsPublished())
            throw new CustomRuntimeException("Not found product id " + id.toString());
        return this.productConverter.toProductDTO(product);
    }
}