package com.winnguyen1905.technologystore.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.winnguyen1905.technologystore.entity.CartEntity;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, UUID> {
    Optional<CartEntity> findByShopIdAndCustomerId(UUID shopId, UUID customerId);
}