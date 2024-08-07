package com.winnguyen1905.technologystore.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.winnguyen1905.technologystore.converter.UserConverter;
import com.winnguyen1905.technologystore.entity.CartEntity;
import com.winnguyen1905.technologystore.entity.CartItemEntity;
import com.winnguyen1905.technologystore.entity.CustomerEntity;
import com.winnguyen1905.technologystore.entity.ProductEntity;
import com.winnguyen1905.technologystore.entity.ShopEntity;
import com.winnguyen1905.technologystore.entity.UserEntity;
import com.winnguyen1905.technologystore.exception.CustomRuntimeException;
import com.winnguyen1905.technologystore.model.dto.CartDTO;
import com.winnguyen1905.technologystore.model.dto.CartItemDTO;
import com.winnguyen1905.technologystore.repository.CartItemRepository;
import com.winnguyen1905.technologystore.repository.CartRepository;
import com.winnguyen1905.technologystore.repository.ProductRepository;
import com.winnguyen1905.technologystore.repository.UserRepository;
import com.winnguyen1905.technologystore.service.ICartService;

@Service
public class CartService implements ICartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    private final UserConverter userConverter;

    public CartService(CartItemRepository cartItemRepository, CartRepository cartRepository,
            UserRepository userRepository, ModelMapper modelMapper, ProductRepository productRepository,
            UserConverter userConverter) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.userConverter = userConverter;
    }

    @Override
    public CartDTO handleAddCart(CartDTO cartDTO, UUID customerId) {
        // Find shop and customer of this CART
        UserEntity shop = this.userRepository.findByIdOrUsername(customerId, "baokhung2k4") // Note: changeID cartDTO.getShop().getId()
                .orElseThrow(() -> new CustomRuntimeException("Not found shop id " + cartDTO.getShop().getId()));
        UserEntity customer = this.userRepository.findByIdOrUsername(customerId, "baokhung2k4")
            .orElseThrow(() -> new CustomRuntimeException("Not found customer id " + customerId));

        // Product is existing ?
        CartItemDTO cartItemDTO = cartDTO.getCartItems().get(0);
        ProductEntity product = this.productRepository.findById(cartItemDTO.getProduct().getId()).orElseThrow(() -> new CustomRuntimeException("Not found product id " + cartItemDTO.getProduct().getId()));

        // The customer and shopowner has CART together before ?
        cartDTO.setCartItems(null);
        cartItemDTO.setProduct(null);
        // CartEntity cart = this.cartRepository.findByShopIdAndCustomerId(cartDTO.getShop().getId(), customerId).orElse(null); // REAL
        CartEntity cart = this.cartRepository.findByCreatedBy("baokhung2k4").orElse(null); // FAKE

        // If no we construct new cart and add new product into this cart
        if (cart == null) {
            CartItemEntity cartItem = this.modelMapper.map(cartItemDTO, CartItemEntity.class);
            cartItem.setProduct(product);
            cart = this.modelMapper.map(cartDTO, CartEntity.class);
            cartItem.setCart(cart);
            cart.addCartItems(cartItem);
            cart.setShop(shop);
            cart.setCustomer(customer);
        } else {
        // Else we will check whether this product has exist in any cart-item in this cart
            CartItemEntity cartItem = this.cartItemRepository.findByCartIdAndProductId(cart.getId(), product.getId()).orElse(null);
            // We will create new cart-item
            if (cartItem == null) {
                cartItem = this.modelMapper.map(cartItemDTO, CartItemEntity.class);
                cartItem.setProduct(product);
                cartItem.setCart(cart);
                cart.addCartItems(cartItem);
            }
            // We adding quantity into cart-item existing
            else cartItem.setQuantity(cartItem.getQuantity() + cartItemDTO.getQuantity()); 
        }
        cart = this.cartRepository.save(cart);
        return this.modelMapper.map(cart, CartDTO.class);
    }

    @Override
    public CartDTO handleGetCartById(UUID cartId, UUID customerId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleGetCartById'");
    }

    @Override
    public CartDTO handleGetAllCart(UUID customerId, Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleGetAllCart'");
    }

}