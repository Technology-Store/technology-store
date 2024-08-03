package com.winnguyen1905.technologystore.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.winnguyen1905.technologystore.entity.ProductEntity;
import com.winnguyen1905.technologystore.repository.custom.ProductRepositoryCustom;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID>, 
    ProductRepositoryCustom, 
    JpaSpecificationExecutor<ProductEntity> {
        void deleteByIdIn(List<UUID> ids);
        Page<ProductEntity> findAll(Specification<ProductEntity> spec, Pageable pageable);
}