package com.winnguyen1905.technologystore.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.winnguyen1905.technologystore.entity.VariationEntity;
import com.winnguyen1905.technologystore.repository.custom.SoftDeleteRepository;

@Repository
public interface VariationRepository extends JpaRepository<VariationEntity, UUID>, SoftDeleteRepository<VariationEntity, UUID> {}