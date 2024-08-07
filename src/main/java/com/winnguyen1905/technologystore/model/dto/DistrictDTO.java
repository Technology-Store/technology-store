package com.winnguyen1905.technologystore.model.dto;

import java.util.UUID;

import lombok.*;

@Setter
@Getter
public class DistrictDTO extends AbstractDTO<DistrictDTO> {
    private UUID id;
    private String name;
    private String code;
}