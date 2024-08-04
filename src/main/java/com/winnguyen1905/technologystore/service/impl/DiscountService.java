package com.winnguyen1905.technologystore.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winnguyen1905.technologystore.entity.DiscountEntity;
import com.winnguyen1905.technologystore.entity.ProductEntity;
import com.winnguyen1905.technologystore.exception.CustomRuntimeException;
import com.winnguyen1905.technologystore.model.dto.DiscountDTO;
import com.winnguyen1905.technologystore.model.dto.ProductDTO;
import com.winnguyen1905.technologystore.model.request.DiscountSearchRequest;
import com.winnguyen1905.technologystore.repository.DiscountRepository;
import com.winnguyen1905.technologystore.repository.ProductRepository;
import com.winnguyen1905.technologystore.service.IDiscountService;

@Service
@Transactional
public class DiscountService implements IDiscountService {

    private final DiscountRepository discountRepository;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    public DiscountService(DiscountRepository discountRepository, ModelMapper modelMapper,
            ProductRepository productRepository) {
        this.discountRepository = discountRepository;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    @Override
    public DiscountDTO handleCreateDiscountCode(DiscountDTO discountDTO, String username) {
        List<UUID> productIds = discountDTO.getProducts().stream().map(item -> item.getId()).toList();
        List<ProductEntity> products = this.productRepository.findByIdInAndCreatedBy(productIds, username);
        if(products.size() != productIds.size())
                throw new CustomRuntimeException("Just found " + products.size() + " / " + productIds.size() + " product");

        discountDTO.setProducts(new HashSet<ProductDTO>());
        DiscountEntity discount = this.modelMapper.map(discountDTO, DiscountEntity.class);
        for(ProductEntity product : products) {
            product.addDiscountCode(discount);
            discount.addProduct(product);
        }

        discount = this.discountRepository.save(discount);

        return this.modelMapper.map(discount, DiscountDTO.class);
    }

    @Override
    public DiscountDTO handleGetDiscount(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleGetDiscount'");
    }

    @Override
    public DiscountDTO handleGetAllDiscount(DiscountSearchRequest discountSearchRequest, Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleGetAllDiscount'");
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

}