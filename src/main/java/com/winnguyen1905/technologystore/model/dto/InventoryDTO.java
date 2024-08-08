package com.winnguyen1905.technologystore.model.dto;

import java.util.List;

import lombok.*;

@Setter
@Getter
public class InventoryDTO extends AbstractDTO<InventoryDTO> {
    private int stock;

    private DistrictDTO district;

    private List<ReservationDTO> reservations;
}