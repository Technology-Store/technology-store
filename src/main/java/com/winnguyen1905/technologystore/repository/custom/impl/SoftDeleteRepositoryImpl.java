package com.winnguyen1905.technologystore.repository.custom.impl;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.winnguyen1905.technologystore.entity.base.BaseEntity;
import com.winnguyen1905.technologystore.repository.custom.SoftDeleteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import lombok.RequiredArgsConstructor;

@Repository
@Transactional
@RequiredArgsConstructor
public class SoftDeleteRepositoryImpl<T extends BaseEntity, ID> implements SoftDeleteRepository<T, ID> {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private final EntityManager entityManager;

    @Override
    @Modifying
    public void softDeleteOne(T entity) {
        if(entity != null) {
            entity.setIsDeleted(true);
            entityManager.merge(entity);
        }
    }

    @Override
    @Modifying
    public void softDeleteMany(List<T> entities) {
        entities.stream().forEach(item -> softDeleteOne(item));
    }

}