package com.winnguyen1905.technologystore.common;

public enum NotificationType {
    ORDER_SUCCESSFULLY("order_successfully"),
    ORDER_FAILED("order_failed"),
    PROMOTION("promotion"),
    SHOP001("shop001");


    private final String notificationType;

    NotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getNotificationType() {
        return notificationType;
    }
}
