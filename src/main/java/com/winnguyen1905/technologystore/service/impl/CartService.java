package com.winnguyen1905.technologystore.service.impl;

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
import com.winnguyen1905.technologystore.exception.CustomRuntimeException;
import com.winnguyen1905.technologystore.model.dto.CartDTO;
import com.winnguyen1905.technologystore.model.dto.CartItemDTO;
import com.winnguyen1905.technologystore.model.dto.PriceStatisticsDTO;
import com.winnguyen1905.technologystore.repository.CartItemRepository;
import com.winnguyen1905.technologystore.repository.CartRepository;
import com.winnguyen1905.technologystore.repository.ProductRepository;
import com.winnguyen1905.technologystore.repository.UserRepository;
import com.winnguyen1905.technologystore.service.ICartService;
import com.winnguyen1905.technologystore.util.ProductUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    @Override
    public CartDTO handleAddCart(CartDTO cartDTO, UUID customerId) {
        // Valid
        UserEntity shop = this.userRepository.findById(cartDTO.getShop().getId()) // Note: changeID cartDTO.getShop().getId()
                .orElseThrow(() -> new CustomRuntimeException("Not found shop id " + cartDTO.getShop().getId()));
        UserEntity customer = this.userRepository.findById(customerId)
                .orElseThrow(() -> new CustomRuntimeException("Not found customer id " + customerId));

        // Product is existing ?
        CartItemDTO cartItemDTO = cartDTO.getCartItems().get(0);
        ProductEntity product = this.productRepository.findById(cartItemDTO.getProduct().getId()).orElseThrow(() -> new CustomRuntimeException("Not found product id " + cartItemDTO.getProduct().getId()));

        // The customer and shopowner has CART together before ?
        CartEntity cart = this.cartRepository.findByShopIdAndCustomerId(cartDTO.getShop().getId(), customerId).orElse(null); // REAL

        cartDTO.setCartItems(null);
        cartItemDTO.setProduct(null);
        if (cart == null) { // If no we construct new cart and add new product Integero this cart
            CartItemEntity cartItem = this.modelMapper.map(cartItemDTO, CartItemEntity.class);
            cart = this.modelMapper.map(cartDTO, CartEntity.class);
            cartItem.fillInRelationShipData(cart, product);
            cart.fillInRelationShipData(customer, shop, List.of(cartItem));
        } else { // Else we will check whether this product has exist in any cart-item in this cart
            CartItemEntity cartItem = this.cartItemRepository.findByCartIdAndProductId(cart.getId(), product.getId()).orElse(null);
            if (cartItem == null) { // We will create new cart-item
                cartItem = this.modelMapper.map(cartItemDTO, CartItemEntity.class);
                cartItem.fillInRelationShipData(cart, product);
                cart.fillInRelationShipData(customer, shop, List.of(cartItem));
            } else cartItem.setQuantity(cartItem.getQuantity() + 1); // We adding quantity into cart-item existing
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
    public PriceStatisticsDTO handleGetPriceStatisticsOfCart(CartDTO cartDTO, UUID customerId) {
        UserEntity user = this.userRepository.findById(customerId).orElseThrow(() -> new CustomRuntimeException("Not found customer id " + customerId));
        CartEntity cart = this.cartRepository.findById(cartDTO.getId()).orElseThrow(() -> new CustomRuntimeException("Not found cart id " + cartDTO.getId()));
        if(!cart.getCustomer().getId().equals(user.getId())) throw new CustomRuntimeException("Not found cart id " + cartDTO.getId());

        // get selected item and check is out of stock ?
        List<CartItemEntity> cartItemsSelected = cart.getCartItems().stream().filter(item -> {
            if(item.getProduct().getInventories().stream().allMatch(inven -> inven.getStock() == 0)) throw new CustomRuntimeException("out of stock of product id: " + item.getProduct().getId());
            return item.getIsSelected();
        }).toList();

        Double totalPriceOfAllProduct = ProductUtils.totalPriceOfAllProduct(cartItemsSelected);

        return PriceStatisticsDTO.builder()
                .amountProductReduced(0.0)
                .amountShipReduced(0.0)
                .finalPrice(totalPriceOfAllProduct)
                .totalPrice(totalPriceOfAllProduct)
                .totalShipPrice(0.0) // Handle after -------------------------------------------------
                .totalDiscountVoucher(0.0).build();
    }

    @Override
    public CartDTO handleGetMyCarts(UUID customerId, Pageable pageable) {
        Page<CartEntity> cartPage = this.cartRepository.findAllByCustomerId(customerId, pageable);
        return this.modelMapper.map(cartPage, CartDTO.class);
    }

    @Override
    public Boolean handleValidateCart(CartDTO cartDTO, UUID customerId) {
        
        return true;
    }

}