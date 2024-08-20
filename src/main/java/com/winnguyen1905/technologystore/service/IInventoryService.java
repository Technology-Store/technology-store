package com.winnguyen1905.technologystore.service;

import java.util.UUID;

import com.winnguyen1905.technologystore.entity.InventoryEntity;

public interface IInventoryService {
    Boolean isAccessStock(InventoryEntity inventory, Integer quantity);
    Boolean handleUpdateInventoryForReservation(UUID inventory, UUID customerId, Integer quantity);
}