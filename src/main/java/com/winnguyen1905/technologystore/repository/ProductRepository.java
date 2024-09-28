package com.winnguyen1905.technologystore.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.winnguyen1905.technologystore.entity.ProductEntity;
import com.winnguyen1905.technologystore.repository.custom.ProductRepositoryCustom;
import com.winnguyen1905.technologystore.repository.custom.SoftDeleteRepository;
import com.winnguyen1905.technologystore.repository.rewriter.ProductQueryRewriter;

@Repository
public interface ProductRepository extends SoftDeleteRepository<ProductEntity, UUID>, JpaRepository<ProductEntity, UUID>, ProductRepositoryCustom, JpaSpecificationExecutor<ProductEntity> {

    void deleteByIdIn(List<UUID> ids);

    Page<ProductEntity> findAll(Specification<ProductEntity> specification, Pageable pageable);

    @Query(
        value = "select p.* from products as p inner join discount_products as dp on dp.product_id = p.id inner join discounts as d on d.id = dp.discount_id where d.id = :discountId and is_published = true",
        nativeQuery = true,
        queryRewriter = ProductQueryRewriter.class
    )
    Page<ProductEntity> findByDiscountIdAndIsPublishedTrue(UUID discountId, Pageable pageable);
    
    List<ProductEntity> findByIdInAndShopId(List<UUID> ids, UUID shopId); // REAL

    List<ProductEntity> findByIdInAndShopIdOrderById(List<UUID> ids, UUID shopId);
   
    Page<ProductEntity> findAllByShopIdAndIsPublishedTrue(UUID shopId, Pageable pageable);

}