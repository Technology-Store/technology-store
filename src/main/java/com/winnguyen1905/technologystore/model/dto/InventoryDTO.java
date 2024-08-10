package com.winnguyen1905.technologystore.model.dto;

import java.util.List;

import lombok.*;

@Setter
@Getter
public class InventoryDTO extends AbstractDTO<InventoryDTO> {
    private Integer stock;

    private DistrictDTO district;

    private List<ReservationDTO> reservations;
}