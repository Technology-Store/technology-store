package com.winnguyen1905.technologystore.repository.custom;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.winnguyen1905.technologystore.entity.base.BaseEntity;

public interface SoftDeleteRepository<T extends BaseEntity, UUID> {
    void softDeleteOne(T entity);
    void softDeleteMany(List<T> entities);
}