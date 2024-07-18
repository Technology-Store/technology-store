package com.winnguyen1905.technologystore.converter;

import org.springframework.stereotype.Component;

import com.winnguyen1905.technologystore.builder.ProductSearchBuilder;
import com.winnguyen1905.technologystore.model.request.ProductSearchRequest;

@Component
public class ProductSearchBuilderConverter {
    public ProductSearchBuilder toProductSearchBuilder(ProductSearchRequest productSearchRequest) {
        ProductSearchBuilder productSearchBuilder = ProductSearchBuilder.builder()
            .name(productSearchRequest.getName())
            .priceFrom(productSearchRequest.getPriceFrom())
            .priceTo(productSearchRequest.getPriceTo())
            .brand(productSearchRequest.getBrand())
            .os(productSearchRequest.getOs())
            .ram(productSearchRequest.getRam())
            .pin(productSearchRequest.getPin())
            .cpu(productSearchRequest.getCpu())
            .gpu(productSearchRequest.getGpu())
            .xSize(productSearchRequest.getXSize())
            .ySize(productSearchRequest.getYSize())
            .panelType(productSearchRequest.getPanelType())
            .resolution(productSearchRequest.getResolution())
            .typeCode(productSearchRequest.getTypeCode())
            .build();
        return productSearchBuilder;
    }
}