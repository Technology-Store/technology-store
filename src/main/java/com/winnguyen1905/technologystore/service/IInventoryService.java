package com.winnguyen1905.technologystore.service;

import java.util.UUID;

public interface IInventoryService {
    Boolean isAccessStock(UUID invetoryId, Integer quantity);    
}