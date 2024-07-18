package com.winnguyen1905.technologystore.builder;

import java.util.ArrayList;
import java.util.List;

import lombok.*;

@Getter
@Setter
@Builder
public class ProductSearchBuilder {

    @Builder.Default
    private List<String> typeCode = new ArrayList<>(); 

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