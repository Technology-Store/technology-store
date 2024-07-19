package com.winnguyen1905.technologystore.model.request;

import java.util.List;

import com.winnguyen1905.technologystore.model.dto.AbstractDTO;

import lombok.*;

@Getter
@Setter
@SuppressWarnings("rawtypes")
public class ProductSearchRequest extends AbstractDTO {

    private List<String> typeCode;

    private String name;

    private Double priceFrom;

    private Double priceTo;

    private String brand;

    private String os;

    private Integer ram;

    private Integer rom;

    private Integer pin;

    private String cpu;

    private String gpu;

    private Double xSize;

    private Double ySize;

    private String panelType;

    private String resolution;
}