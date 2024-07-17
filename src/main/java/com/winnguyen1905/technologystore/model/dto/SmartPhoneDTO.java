package com.winnguyen1905.technologystore.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Getter
@Setter
// @Builder
public class SmartPhoneDTO extends ProductDTO {
    
    private String os;

    private Integer ram;

    private Integer rom;

    private Integer pin;

    private String cpu;

    private String gpu;

    @JsonProperty("x_size")
    private Double xSize;

    @JsonProperty("y_size")
    private Double ySize;

    @JsonProperty("panel_type")
    private String panelType;

    private String resolution;

}
