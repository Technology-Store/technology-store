package com.winnguyen1905.technologystore.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.winnguyen1905.technologystore.entity.CartEntity;
import com.winnguyen1905.technologystore.entity.CartItemEntity;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, UUID> {
    Optional<CartItemEntity> findByCartIdAndProductId(UUID cartId, UUID productId);
    Optional<CartItemEntity> findByCreatedBy(String createdBy);
}