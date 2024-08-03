package com.winnguyen1905.technologystore.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.winnguyen1905.technologystore.entity.ProductEntity;
import com.winnguyen1905.technologystore.repository.custom.ProductRepositoryCustom;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID>,  ProductRepositoryCustom, JpaSpecificationExecutor<ProductEntity> {
    void deleteByIdIn(List<UUID> ids);
    String publishProductSQL = "update product as p set p.is_published = TRUE, p.is_draft = 0 where p.created_by = :shopOwner and p.id in :ids";
    @Modifying
    @Query(value = publishProductSQL, nativeQuery = true)
    int publishProductsByShop(List<UUID> ids, String shopOwner);
    List<ProductEntity> findByIdInAndCreatedBy(List<UUID> ids, String shopOwner);
    Page<ProductEntity> findAll(Specification<ProductEntity> spec, Pageable pageable);
}