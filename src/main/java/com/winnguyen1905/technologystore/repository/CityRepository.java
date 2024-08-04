package com.winnguyen1905.technologystore.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.winnguyen1905.technologystore.entity.CityEntity;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, UUID> {}