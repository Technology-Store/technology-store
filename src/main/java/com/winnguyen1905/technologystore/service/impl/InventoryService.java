package com.winnguyen1905.technologystore.service.impl;

import java.util.UUID;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.lang.Nullable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Service;

import com.winnguyen1905.technologystore.entity.InventoryEntity;
import com.winnguyen1905.technologystore.entity.ProductEntity;
import com.winnguyen1905.technologystore.exception.CustomRuntimeException;
import com.winnguyen1905.technologystore.repository.InventoryRepository;
import com.winnguyen1905.technologystore.service.IInventoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService implements IInventoryService {

    // private final RedisTemplate<String, Integer> redisTemplate;
    // private final InventoryRepository inventoryRepository;
    // private final String prefixProduct = "product_id:";

    @Override
    public Boolean isAccessStock(UUID invetoryId, Integer quantity) {
        // InventoryEntity inventory = this.inventoryRepository.findById(invetoryId)
        //         .orElseThrow(() -> new CustomRuntimeException("Not found inventory id " + invetoryId));
        // ProductEntity product = inventory.getProduct();
        
        // return redisTemplate.execute(new SessionCallback<Boolean>() {

        //     @Override
        //     @Nullable
        //     public <K, V> Boolean execute(RedisOperations<K, V> operations) throws DataAccessException {
        //         // TODO Auto-generated method stub
        //         throw new UnsupportedOperationException("Unimplemented method 'execute'");
        //     }
        // });
        return null;
    }

    

}