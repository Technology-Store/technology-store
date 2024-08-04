package com.winnguyen1905.technologystore.model.dto;

import java.util.UUID;

import lombok.*;

@Setter
@Getter
@Builder
public class CityDTO extends AbstractDTO<CityDTO> {
    private UUID id;
    private String name;
    private String code;
}