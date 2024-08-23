package com.winnguyen1905.technologystore.common;

public enum OrderStatus {
    SPENDING("spending"),
    CONFIRMED("confirmed"),
    SHIPED("shiped"),
    CANCELLED("cancelled"),
    DELIVERED("delivered");

    private final String orderStatus;

    OrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    String getOrderStatus() {
        return this.orderStatus;
    }
}