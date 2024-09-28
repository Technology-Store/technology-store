package com.winnguyen1905.technologystore.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Getter
@Setter
public class ElectronicDTO extends ProductDTO {
    private String os;

    private Integer ram;

    private Integer rom;

    private Integer pin;

    private String chipset;

    @JsonProperty("device_size")
    private String deviceSize;

    private String display;
}