package com.winnguyen1905.technologystore.common;

import java.util.Map;
import java.util.TreeMap;

public enum ApplyDiscountType {
    ALL("all"),
    SPECIFIC("specific");

    private final String applyDiscountType;

    ApplyDiscountType(String applyDiscountType) {
        this.applyDiscountType = applyDiscountType;
    }

    public String getApplyDiscountType() {
        return applyDiscountType;
    }
}