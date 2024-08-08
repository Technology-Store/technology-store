package com.winnguyen1905.technologystore.model.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Getter
@Setter
public class SmartPhoneDTO extends ProductDTO {
    private String os;

    private int ram;

    private int rom;

    private int pin;

    private String chipset;

    @JsonProperty("device_size")
    private String deviceSize;

    private String display;
}