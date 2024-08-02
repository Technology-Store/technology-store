package com.winnguyen1905.technologystore.common;

import java.util.Map;
import java.util.TreeMap;

public enum PaymentMethod {
    CASH("cash"),
    BANKING("banking");

    private final String paymentMethod;

    PaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethodType() {
        return paymentMethod;
    }

    public static Map<String,String> type() {
        Map<String, String> listpaymentMethodType = new TreeMap<>();
        for(PaymentMethod item : PaymentMethod.values()){
            listpaymentMethodType.put(item.toString() , item.getPaymentMethodType());
        }
        return listpaymentMethodType;
    }
}