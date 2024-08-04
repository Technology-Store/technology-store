package com.winnguyen1905.technologystore.common;

import java.util.Map;
import java.util.TreeMap;

public enum DiscountAppliesType {
    ALL("all"),
    SPECIFIC("specific");

    private final String discountAppliesType;

    DiscountAppliesType(String discountAppliesType) {
        this.discountAppliesType = discountAppliesType;
    }

    public String getdiscountAppliesType() {
        return discountAppliesType;
    }
}