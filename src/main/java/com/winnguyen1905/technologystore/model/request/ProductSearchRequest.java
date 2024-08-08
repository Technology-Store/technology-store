package com.winnguyen1905.technologystore.model.request;

import java.util.List;

import com.winnguyen1905.technologystore.model.dto.AbstractDTO;

import lombok.*;

@Getter
@Setter
public class ProductSearchRequest extends AbstractDTO<ProductSearchRequest> {
    private List<String> typeCode;

    private String name;

    private double priceFrom;

    private double priceTo;

    private String brand;

    private String os;

    private int ram;

    private int rom;

    private int pin;

    private String cpu;

    private String gpu;

    private String chipset;

    private double xSize;

    private double ySize;

    private String panelType;

    private String resolution;

    private Boolean isDraft;

    private Boolean isPublished;
}