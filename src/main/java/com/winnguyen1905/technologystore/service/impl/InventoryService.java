package com.winnguyen1905.technologystore.service.impl;

import java.util.UUID;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Service;

import com.winnguyen1905.technologystore.entity.InventoryEntity;
import com.winnguyen1905.technologystore.entity.ProductEntity;
import com.winnguyen1905.technologystore.entity.ReservationEntity;
import com.winnguyen1905.technologystore.entity.UserEntity;
import com.winnguyen1905.technologystore.exception.CustomRuntimeException;
import com.winnguyen1905.technologystore.repository.InventoryRepository;
import com.winnguyen1905.technologystore.repository.ReservationRepository;
import com.winnguyen1905.technologystore.repository.UserRepository;
import com.winnguyen1905.technologystore.service.IInventoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService implements IInventoryService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final InventoryRepository inventoryRepository;
    private final ReservationRepository reservationRepository;
    private final String prefixProduct = "product_id:";
    private static final Integer MAX_RETRIES = 10;
    private final UserRepository userRepository;

    @Override
    public Boolean isAccessStock(InventoryEntity inventory, Integer quantity) {
        ProductEntity product = inventory.getProduct();

        String key = this.prefixProduct + product.getId();
        Boolean isExistKey = this.redisTemplate.hasKey(key);

        if(!Boolean.TRUE.equals(isExistKey)) this.redisTemplate.opsForValue().set(key, inventory.getStock());
        Boolean ans = false;
        for(int i = 0; i < this.MAX_RETRIES && !false; i++) {
            ans = (Boolean) this.redisTemplate.execute((RedisCallback<Object>) connection -> {
                connection.watch(key.getBytes());
                Integer currentQuantity = (Integer) this.redisTemplate.opsForValue().get(key);

                if(currentQuantity < quantity) return false;
                this.redisTemplate.multi();
                this.redisTemplate.opsForValue().set(key, currentQuantity - quantity);

                return this.redisTemplate.exec() != null;
            });
        }
        return ans;
    }

    @Override
    public Boolean handleUpdateInventoryForReservation(UUID inventoryId, UUID customerId, Integer quantity) {
        UserEntity user = this.userRepository.findById(customerId)
                .orElseThrow(() -> new CustomRuntimeException("Not found user id " + customerId));
        InventoryEntity inventory = this.inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new CustomRuntimeException("Not found inventory id " + inventoryId));

        if(!isAccessStock(inventory, quantity)) return false;

        inventory.setStock(inventory.getStock() - quantity);
        ReservationEntity reservation = new ReservationEntity();
        reservation.setCustomer(user);
        reservation.setInventory(inventory);
        inventory.getReservations().add(reservation);
        this.inventoryRepository.save(inventory);

        return true;
    }

}