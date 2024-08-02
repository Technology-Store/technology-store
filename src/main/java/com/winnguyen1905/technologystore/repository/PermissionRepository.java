package com.winnguyen1905.technologystore.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.winnguyen1905.technologystore.entity.PermissionEntity;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, UUID>, JpaSpecificationExecutor<PermissionEntity> {
    void deleteByIdIn(List<UUID> ids);
    List<PermissionEntity> findByCodeIn(List<String> code);
}