package com.winnguyen1905.technologystore.model.dto;

import java.util.List;
import java.util.UUID;

import com.winnguyen1905.technologystore.entity.DistrictEntity;
import com.winnguyen1905.technologystore.entity.ProductEntity;
import com.winnguyen1905.technologystore.entity.ShopEntity;

import lombok.*;

@Setter
@Getter
public class InventoryDTO extends AbstractDTO<InventoryDTO> {
    private Integer stock;

    private DistrictEntity district;

    private List<ReservationDTO> reservations;

    private ProductEntity product;

    private ShopEntity shop;
}