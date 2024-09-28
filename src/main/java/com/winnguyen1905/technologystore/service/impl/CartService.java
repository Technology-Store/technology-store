package com.winnguyen1905.technologystore.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.winnguyen1905.technologystore.converter.UserConverter;
import com.winnguyen1905.technologystore.entity.CartEntity;
import com.winnguyen1905.technologystore.entity.CartItemEntity;
import com.winnguyen1905.technologystore.entity.CustomerEntity;
import com.winnguyen1905.technologystore.entity.ProductEntity;
import com.winnguyen1905.technologystore.entity.ShopEntity;
import com.winnguyen1905.technologystore.entity.UserEntity;
import com.winnguyen1905.technologystore.entity.VariationEntity;
import com.winnguyen1905.technologystore.exception.CustomRuntimeException;
import com.winnguyen1905.technologystore.model.dto.CartDTO;
import com.winnguyen1905.technologystore.model.dto.CartItemDTO;
import com.winnguyen1905.technologystore.model.dto.PriceStatisticsDTO;
import com.winnguyen1905.technologystore.model.dto.UserDTO;
import com.winnguyen1905.technologystore.model.request.AddToCartRequest;
import com.winnguyen1905.technologystore.model.response.PaginationResponse;
import com.winnguyen1905.technologystore.repository.CartItemRepository;
import com.winnguyen1905.technologystore.repository.CartRepository;
import com.winnguyen1905.technologystore.repository.ProductRepository;
import com.winnguyen1905.technologystore.repository.UserRepository;
import com.winnguyen1905.technologystore.repository.VariationRepository;
import com.winnguyen1905.technologystore.service.ICartService;
import com.winnguyen1905.technologystore.util.CartUtils;
import com.winnguyen1905.technologystore.util.PaginationUtils;
import com.winnguyen1905.technologystore.util.ProductUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final VariationRepository variationRepository;

    @Override
    public void handleAddToCart(UUID customerId, AddToCartRequest addToCartRequest) {
        UserEntity customer = this.userRepository.findById(customerId).orElseThrow(() -> new CustomRuntimeException("Not found customer id " + customerId));
        VariationEntity variation = this.variationRepository.findById(addToCartRequest.getVariationId()).orElseThrow(() -> new CustomRuntimeException("Not found product variation id "));
        UserEntity shop = variation.getProduct().getShop();

        CartEntity cart = this.cartRepository.findByShopIdAndCustomerId(shop.getId(), customerId).orElse(null);

        if (cart == null) {
            cart = new CartEntity();
            cart.setShop(shop);
            cart.setCustomer(customer);
        }

        CartItemEntity newCartItem = this.cartItemRepository.findByCartIdAndVariationId(cart.getId(), variation.getId()).orElse(null);

        if (newCartItem == null) {
            newCartItem = new CartItemEntity();
            newCartItem.setCart(cart);
            newCartItem.setProductVariation(variation);
        }

        newCartItem.setQuantity(newCartItem.getQuantity() + addToCartRequest.getQuantity());
        cart.getCartItems().add(newCartItem);

        cart = this.cartRepository.save(cart);
    }

    // not fixed yet ----------------------------------------------------------------------------------------------
    public List<CartDTO> handleGetCartReview(UUID customerId, Pageable page) {
        // UserEntity customer = this.userRepository.findById(customerId).orElseThrow(() -> new CustomRuntimeException("Not found customer id " + customerId));
        Page<CartEntity> carts = this.cartRepository.findAllByCustomerId(customerId, page);
        List<CartDTO> cartDTOs = carts.stream().map(cart -> {
            CartDTO cartDTO = this.modelMapper.map(cart, CartDTO.class);
            return cartDTO;
        }).toList();
        return cartDTOs;
    }

    @Override
    public CartDTO handleGetCartById(UUID cartId, UUID customerId) {
        return null;
    }

    @Override
    public PriceStatisticsDTO handleGetPriceStatisticsOfCart(UUID customerId, UUID cartId) {
        CartEntity cart = this.cartRepository.findById(cartId).orElseThrow(() -> new CustomRuntimeException("Not found cart id " + cartId));

        if(!cart.getCustomer().getId().equals(customerId)) throw new CustomRuntimeException("Not found cart id " + cartId);

        PriceStatisticsDTO priceStatisticsDTO = CartUtils.getPriceStatisticsOfCart(cart);
        return priceStatisticsDTO;
    }

    @Override
    public PaginationResponse<CartDTO> handleGetCarts(UUID customerId, Pageable pageable) {
        UserEntity customer = this.userRepository.findById(customerId).orElseThrow(() -> new CustomRuntimeException("Not found customer id " + customerId));
        Page<CartEntity> cartPage = this.cartRepository.findAllByCustomerId(customer.getId(), pageable);
        PaginationResponse<CartDTO> cartResponse = PaginationUtils.rawPaginationResponse(cartPage);

        List<CartDTO> cartDTOs = new ArrayList<CartDTO>();
        cartPage.getContent().stream().forEach(cart -> {
            CartDTO cartDTO = this.modelMapper.map(cart, CartDTO.class);
            PriceStatisticsDTO PriceStatistic = CartUtils.getPriceStatisticsOfCart(cart);
            cartDTO.setPriceStatistic(PriceStatistic);
            cartDTOs.add(cartDTO);
        });
        cartResponse.setResults(cartDTOs);

        return cartResponse;
    }

    @Override
    public Boolean handleValidateCart(CartDTO cartDTO, UUID customerId) {
        return true;
    }

}