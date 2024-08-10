package com.winnguyen1905.technologystore.common;

public enum ApplyDiscountStatus {
    REVIEW("review"),
    COMMIT("commit");

    private final String ApplyDiscountStatus;

    ApplyDiscountStatus(String ApplyDiscountStatus) {
        this.ApplyDiscountStatus = ApplyDiscountStatus;
    }

    public String getApplyDiscountStatus() {
        return this.ApplyDiscountStatus;
    }
}