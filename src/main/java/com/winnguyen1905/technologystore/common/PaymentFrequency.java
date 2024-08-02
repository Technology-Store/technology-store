package com.winnguyen1905.technologystore.common;

import java.util.Map;
import java.util.TreeMap;

public enum PaymentFrequency {
    ONE_TIME("one_time"),
    MONTHLY("monthly");

    private final String paymentFrequencyType;

    PaymentFrequency(String paymentFrequencyType) {
        this.paymentFrequencyType = paymentFrequencyType;
    }

    public String getPaymentFrequencyType() {
        return paymentFrequencyType;
    }

    public static Map<String,String> type() {
        Map<String, String> listPaymentFrequencyType = new TreeMap<>();
        for(PaymentFrequency item : PaymentFrequency.values()){
            listPaymentFrequencyType.put(item.toString() , item.getPaymentFrequencyType());
        }
        return listPaymentFrequencyType;
    }
}