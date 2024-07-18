package com.winnguyen1905.technologystore.repository.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.winnguyen1905.technologystore.repository.custom.ProductRepositoryCustom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;

@Primary
@Repository
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {
    @Autowired
    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    EntityManager entityManager;
}