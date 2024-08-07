package com.winnguyen1905.technologystore.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.winnguyen1905.technologystore.entity.DiscountEntity;

@Repository
public interface DiscountRepository extends JpaRepository<DiscountEntity, UUID>, JpaSpecificationExecutor<DiscountEntity> {
    void deleteByIdIn(List<UUID> ids);

    DiscountEntity findByCodeAndShopId(String code, UUID shopId);

    Optional<DiscountEntity> findByIdAndIsActiveTrue(UUID id);

    Page<DiscountEntity> findAllByShopIdAndIsActiveTrue(UUID shopId, Pageable pageable);
}