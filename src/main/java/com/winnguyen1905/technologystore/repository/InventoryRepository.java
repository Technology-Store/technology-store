package com.winnguyen1905.technologystore.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.winnguyen1905.technologystore.entity.InventoryEntity;

public interface InventoryRepository extends JpaRepository<InventoryEntity, UUID> {}