package com.winnguyen1905.technologystore.service.impl;

import java.time.Instant;
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
import com.winnguyen1905.technologystore.common.DiscountType;
import com.winnguyen1905.technologystore.entity.CartEntity;
import com.winnguyen1905.technologystore.entity.CartItemEntity;
import com.winnguyen1905.technologystore.entity.DiscountEntity;
import com.winnguyen1905.technologystore.entity.ProductEntity;
import com.winnguyen1905.technologystore.entity.UserEntity;
import com.winnguyen1905.technologystore.exception.CustomRuntimeException;
import com.winnguyen1905.technologystore.model.dto.CartDTO;
import com.winnguyen1905.technologystore.model.dto.DiscountDTO;
import com.winnguyen1905.technologystore.model.dto.ProductDTO;
import com.winnguyen1905.technologystore.model.request.DiscountSearchRequest;
import com.winnguyen1905.technologystore.model.response.ApplyDiscountResponse;
import com.winnguyen1905.technologystore.repository.CartRepository;
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
    private final CartRepository cartRepository;

    public DiscountService(DiscountRepository discountRepository,
            ModelMapper modelMapper, ProductRepository productRepository, UserRepository userRepository,
            CartRepository cartRepository) {
        this.discountRepository = discountRepository;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public DiscountDTO handleCreateDiscountCode(DiscountDTO discountDTO, UUID shopId) {
        UserEntity shop = this.userRepository.findByIdOrUsername(shopId, "baokhung2k4").orElseThrow(() -> new CustomRuntimeException("Not found shop id " + shopId));
        List<UUID> productIds = 
                discountDTO.getProducts() != null
                ? discountDTO.getProducts().stream().map(item -> item.getId()).toList()
                : new ArrayList<>();
        discountDTO.setProducts(null);
        DiscountEntity discount = this.modelMapper.map(discountDTO, DiscountEntity.class);

        if (discountDTO.getAppliesTo().equals(DiscountAppliesType.SPECIFIC)) {
            List<ProductEntity> products = this.productRepository.findByIdInAndShopId(productIds, shopId);
            if (products.size() != productIds.size()) throw new CustomRuntimeException("Just found " + products.size() + " / " + productIds.size() + " product");
            for (ProductEntity product : products) {
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
        DiscountEntity discount = this.discountRepository.findByCodeAndShopIdOrCreatedBy(code, shopId, "baokhung2k4");
        if (discount == null || !discount.getIsActive()) throw new CustomRuntimeException("Not found discount with code " + code);

        if (discount.getAppliesTo().equals(DiscountAppliesType.ALL)) {
            List<ProductEntity> products = this.productRepository.findAllByShopIdAndIsPublishedTrue(shopId, pageable);
            discount.setProducts(Set.copyOf(products));
        }
        return this.modelMapper.map(discount, DiscountDTO.class);
    }

    @Override
    public ApplyDiscountResponse handleApplyDiscountForCart(DiscountDTO discountDTO, UUID customerId) {
        UserEntity customer = this.userRepository.findByIdOrUsername(customerId, "baokhung2k4").orElseThrow(() -> new CustomRuntimeException("Not found current user"));
        DiscountEntity discount = this.discountRepository.findByIdAndIsActiveTrue(discountDTO.getId()).orElseThrow(() -> new CustomRuntimeException("Not found discount id " + discountDTO.getId()));

        // Valid
        long timeUsed = discount.getCustomer().stream().filter(item -> item.equals(customer)).count();
        if(discount.getMaxUsesPerUser() == timeUsed) throw new CustomRuntimeException("Use count reach to maximum");
        if(discount.getEndDate().isBefore(Instant.now())) throw new CustomRuntimeException("Discount has been expired");
        if(discount.getUsesCount() >= discount.getMaxUses()) throw new CustomRuntimeException("Discount be used maximum");

        // Find cart that match with this USER AND DISCOUNT
        CartEntity cart = this.cartRepository.findByShopIdAndCustomerId(discount.getShop().getId(), customer.getId()).orElseThrow(() -> new CustomRuntimeException("Not found cart"));

        // Get all cart-item selected
        List<CartItemEntity> cartItemsSelected = cart.getCartItems().stream().filter(item -> item.getIsSelected()).toList();

        // Get total price of all product in the discount program
        double totalPrice = 0.0;
        if(discount.getAppliesTo().equals(DiscountAppliesType.SPECIFIC)) {
            for(CartItemEntity cartItem : cartItemsSelected) {
                if(discount.getProducts().contains(cartItem.getProduct())) totalPrice += cartItem.getQuantity() * cartItem.getProduct().getPrice();
            }
        } else for(CartItemEntity cartItem : cartItemsSelected) totalPrice += cartItem.getQuantity() * cartItem.getProduct().getPrice();

        // Reach to the Lowest price possible to be discount
        if(discount.getMinOrderValue() > totalPrice) throw new CustomRuntimeException("The total price of product selected in this cart insufficient");

        // Update discount data before sending response
        discount.getCustomer().add(customer);
        discount.setUsesCount(discount.getUsesCount() - 1);
        discountRepository.save(discount);

        // Get the final total price
        double finalPrice = discount.getDiscountType().equals(DiscountType.FIXED_AMOUNT) 
                ? discount.getValue()
                : totalPrice * (1 - discount.getValue() / 100);
        return ApplyDiscountResponse.builder()
                .finalPrice(finalPrice).totalPrice(totalPrice)
                .cart(this.modelMapper.map(cart, CartDTO.class)).build();
    }

    @Override
    public void handleCancelDiscountForCart(DiscountDTO discountDTO, UUID customerId) {
        UserEntity customer = this.userRepository.findByIdOrUsername(customerId, "baokhung2k4").orElseThrow(() -> new CustomRuntimeException("Not found current user"));
        DiscountEntity discount = this.discountRepository.findByIdAndIsActiveTrue(discountDTO.getId()).orElseThrow(() -> new CustomRuntimeException("Not found discount id " + discountDTO.getId()));
        // We can add relationship between CART and DISCOUNT for easy to analyze when toggle cart page
        // CartEntity cart = this.cartRepository.findByShopIdAndCustomerId(discount.getShop().getId(), customer.getId()).orElseThrow(() -> new CustomRuntimeException("Not found cart"));

        boolean res = discount.getCustomer().remove(customer);
        if(!res) throw new CustomRuntimeException("Cancel discount for cart failed");
        discount.setUsesCount(discount.getUsesCount() - 1);

        discountRepository.save(discount);
    }

}