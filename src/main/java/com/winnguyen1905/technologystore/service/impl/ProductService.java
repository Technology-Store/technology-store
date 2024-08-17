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

import com.winnguyen1905.technologystore.converter.ProductConverter;
import com.winnguyen1905.technologystore.entity.InventoryEntity;
import com.winnguyen1905.technologystore.entity.ProductEntity;
import com.winnguyen1905.technologystore.entity.UserEntity;
import com.winnguyen1905.technologystore.exception.CustomRuntimeException;
import com.winnguyen1905.technologystore.model.dto.ProductDTO;
import com.winnguyen1905.technologystore.model.request.ProductRequest;
import com.winnguyen1905.technologystore.model.request.ProductSearchRequest;
import com.winnguyen1905.technologystore.repository.ProductRepository;
import com.winnguyen1905.technologystore.repository.UserRepository;
import com.winnguyen1905.technologystore.service.IProductService;
import com.winnguyen1905.technologystore.util.NormalSpecificationUtils;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Override
    public ProductDTO handleAddProduct(ProductRequest productRequest, UUID shopId) {
        ProductEntity product = productConverter.toProductEntity(productRequest);
        UserEntity shop = this.userRepository.findByIdOrUsername(shopId, "baokhung2k4").orElseThrow(() -> new CustomRuntimeException("not found shop id " + shopId));
        product.setShop(shop);
        for(InventoryEntity inventory : product.getInventories()) {
            inventory.setProduct(product);
            inventory.setShop(shop);
        }
        product = this.productRepository.save(product);
        return this.productConverter.toProductDTO(product);
    }

    @Override
    public List<ProductDTO> handleUpdateProducts(List<ProductRequest> productRequests, UUID shopId) {
        List<UUID> ids = productRequests.stream().map(item -> item.getId()).toList();
        List<ProductEntity> products = this.productRepository.findByIdInAndShopIdOrderById(ids, shopId);
        if(products.size() != ids.size()) throw new CustomRuntimeException("Cannot update because " + products.size() + " of " + ids.size() + " product be found");

        List<ProductEntity> newDataOfProducts = productRequests.stream()
                .map(item -> (ProductEntity) this.productConverter.toProductEntity(item))
                .sorted(Comparator.comparing(ProductEntity::getId)).collect(Collectors.toList());
        for (Integer i = 0; i < products.size(); i++) {
            ProductEntity oldData = products.get(i), newData = newDataOfProducts.get(i);
            this.modelMapper.map(newData, oldData);
            products.set(i, oldData);
        }
        products = this.productRepository.saveAll(products);
        return products.stream().map(item -> (ProductDTO) this.productConverter.toProductDTO(item)).toList();
    }

    @Override
    public ProductDTO handleGetAllProducts(ProductSearchRequest productSearchRequest, Pageable pageable) {
        List<Specification<ProductEntity>> specList = NormalSpecificationUtils.toNormalSpec(productSearchRequest);
        Page<ProductEntity> productPages = this.productRepository.findAll(Specification.allOf(specList), pageable);
        return this.modelMapper.map(productPages, ProductDTO.class);
    }

    @Override
    public List<ProductDTO> handleChangeProductStatus(List<UUID> ids, UUID shopId) {
        List<ProductEntity> products = this.productRepository.findByIdInAndShopId(ids, shopId);
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

    @Override
    public void handleDeleteProducts(List<UUID> ids, UUID shopId) {
        List<ProductEntity> products = this.productRepository.findByIdInAndShopId(ids, shopId);
        this.productRepository.softDeleteMany(products);
    }
}