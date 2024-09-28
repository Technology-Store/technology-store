package com.winnguyen1905.technologystore.model.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VariationDTO extends AbstractDTO {
    private String detail;
    private List<InventoryDTO> inventories;
}