package com.winnguyen1905.technologystore.common;

public enum DiscountType {
    FIXED_AMOUNT("fixed_amount"),
    PERCENTAGE("percentage");

    private final String discountType;

    DiscountType(String discountType) {
        this.discountType = discountType;
    }

    public String getDiscountType() {
        return this.discountType;
    }
}