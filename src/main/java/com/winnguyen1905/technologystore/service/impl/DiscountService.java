package com.winnguyen1905.technologystore.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winnguyen1905.technologystore.common.DiscountAppliesType;
import com.winnguyen1905.technologystore.entity.DiscountEntity;
import com.winnguyen1905.technologystore.entity.ProductEntity;
import com.winnguyen1905.technologystore.entity.UserEntity;
import com.winnguyen1905.technologystore.exception.CustomRuntimeException;
import com.winnguyen1905.technologystore.model.dto.DiscountDTO;
import com.winnguyen1905.technologystore.model.dto.ProductDTO;
import com.winnguyen1905.technologystore.model.request.DiscountSearchRequest;
import com.winnguyen1905.technologystore.repository.DiscountRepository;
import com.winnguyen1905.technologystore.repository.ProductRepository;
import com.winnguyen1905.technologystore.repository.UserRepository;
import com.winnguyen1905.technologystore.service.IDiscountService;

@Service
@Transactional
public class DiscountService implements IDiscountService {

    private final DiscountRepository discountRepository;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public DiscountService(DiscountRepository discountRepository, 
        ModelMapper modelMapper, ProductRepository productRepository, UserRepository userRepository
    ) {
        this.discountRepository = discountRepository;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public DiscountDTO handleCreateDiscountCode(DiscountDTO discountDTO, UUID shopId) {
        UserEntity shop = this.userRepository.findByIdOrUsername(shopId, "baokhung2k4").orElseThrow(() -> new CustomRuntimeException("Not found shop id " + shopId));
        List<UUID> productIds =  discountDTO.getProducts() != null ? discountDTO.getProducts().stream().map(item -> item.getId()).toList() : new ArrayList<>();
        discountDTO.setProducts(null);
        DiscountEntity discount = this.modelMapper.map(discountDTO, DiscountEntity.class);
        
        if(discountDTO.getAppliesTo().equals(DiscountAppliesType.SPECIFIC)) {
            List<ProductEntity> products = this.productRepository.findByIdInAndShopId(productIds, shopId);
            if(products.size() != productIds.size()) throw new CustomRuntimeException("Just found " + products.size() + " / " + productIds.size() + " product");
            for(ProductEntity product : products) {
                product.addDiscountCode(discount);
                discount.addProduct(product);
            }
        }
        
        discount.setShop(shop);
        discount = this.discountRepository.save(discount);
        return this.modelMapper.map(discount, DiscountDTO.class);
    }

    @Override
    public DiscountDTO handleGetDiscount(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleGetDiscount'");
    }

    @Override
    public DiscountDTO handleGetAllDiscountCodesByShop(UUID shopId, Pageable pageable) {
        Page<DiscountEntity> discount = this.discountRepository.findAllByShopIdAndIsActiveTrue(shopId, pageable);
        discount.getContent().forEach(item -> item.setProducts(null));
        return this.modelMapper.map(discount, DiscountDTO.class);
    }

    @Override
    public Boolean handleVerifyDiscountCode(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleVerifyDiscountCode'");
    }

    @Override
    public void handleDeleteDiscountCode(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleDeleteDiscountCode'");
    }

    @Override
    public void handleCancelDiscountCode(UUID id, String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleCancelDiscountCode'");
    }

    @Override
    public DiscountDTO handleGetAllDiscountCodeWithProducts(String code, UUID shopId, Pageable pageable) {
        DiscountEntity discount = this.discountRepository.findByCodeAndShopId(code, shopId);
        if(discount == null || !discount.getIsActive()) throw new CustomRuntimeException("Not found discount with code " + code);
        
        if(discount.getAppliesTo().equals(DiscountAppliesType.ALL)) {
            List<ProductEntity> products = this.productRepository.findAllByShopIdAndIsPublishedTrue(shopId, pageable);
            discount.setProducts(Set.copyOf(products));
        }
        return this.modelMapper.map(discount, DiscountDTO.class);
    }

}