package com.winnguyen1905.technologystore.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.winnguyen1905.technologystore.entity.CommentEntity;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, UUID> {
    @Query(
        value = """
            select af.* from
            (select c.* from comments as c order by c.comment_right desc) as af
            where af.product_id = :productId
            limit 1
        """, 
        nativeQuery = true
    )
    CommentEntity findOneWithHighestRightAndProductId(UUID productId);

    Page<CommentEntity> findAllByParentCommentId(UUID parentId, Pageable pageable);
}