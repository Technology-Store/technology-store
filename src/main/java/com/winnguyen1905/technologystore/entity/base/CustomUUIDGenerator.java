package com.winnguyen1905.technologystore.entity.base;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

// I use custom uuid for easier mock test
public class CustomUUIDGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        BaseEntity entity = (BaseEntity) object;
        if(entity.getId() != null) {
            return entity.getId();
        } else return UUID.randomUUID();
    }

}