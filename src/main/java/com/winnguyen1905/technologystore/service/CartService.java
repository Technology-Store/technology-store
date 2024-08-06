package com.winnguyen1905.technologystore.service;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

@Service
public class CartService implements ICartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    public CartService(CartItemRepository cartItemRepository, CartRepository cartRepository,
            UserRepository userRepository, ModelMapper modelMapper, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    @Override
    public CartDTO handleAddCart(CartDTO cartDTO, UUID customerId) {
        ShopEntity shop = (ShopEntity) this.userRepository.findById(cartDTO.getId())
                .orElseThrow(() -> new CustomRuntimeException("Not found shop id " + cartDTO.getShop().getId()));
        CustomerEntity customer = (CustomerEntity) this.userRepository.findById(customerId)
                .orElseThrow(() -> new CustomRuntimeException("Not found customer id " + customerId));
        cartDTO.setShop(null);

        CartItemDTO cartItemDTO = cartDTO.getCartItems().get(0);
        cartDTO.setCartItems(null);
        ProductEntity product = this.productRepository.findById(cartItemDTO.getProduct().getId())
                .orElseThrow(() -> new CustomRuntimeException("Not found product id " + cartItemDTO.getProduct().getId())); cartItemDTO.setProduct(null);

        CartItemEntity cartItem = this.cartItemRepository.findByCartIdAndProductId(cartItemDTO.getId(), product.getId()).orElseThrow(null);
        if(cartItem == null) {
            cartItem = this.modelMapper.map(cartItemDTO, CartItemEntity.class);
            cartItem.setProduct(product);
            product.addCartItem(cartItem);
        }

        // Is existing cart ?
        CartEntity cart = this.cartRepository.findByShopIdAndCustomerId(cartDTO.getShop().getId(), customerId).orElse(null);
        if(cart == null) { // Yes, we will create new cart
            // Adding a cart is just a task where the user clicks to add a product from a shop to the cart, Therefore we do not need to traverse a list
            cartDTO.setCartItems(null);
            cart = this.modelMapper.map(cartDTO, CartEntity.class);
            cart.setCartItems(List.of(cartItem));
            cart.setShop(shop);
            cart.setCustomer(customer);
        } else { // We have to check product is exist in this cart, if so add one
            if(cartItem.getCart() == null) {
                cartItem.setCart(cart);
            } else {
                cartItem.setQuantity(cartItem.getQuantity() + cartItemDTO.getQuantity());
                this.cartItemRepository.save(cartItem);
            }
        }

        this.cartRepository.save(cart);
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