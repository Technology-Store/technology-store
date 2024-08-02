package com.winnguyen1905.technologystore.common;

import java.util.Map;
import java.util.TreeMap;

public enum TransactionType {
    DEPOSIT("deposit"),
    INSPECTION("inspection"),
    CONSULT("consult");

    private final String transactionType;

    TransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionTypeName() {
        return this.transactionType;
    }

    public static Map<String,String> type() {
        Map<String, String> map = new TreeMap<>();
        for(TransactionType item : TransactionType.values()) {
            map.put(item.toString() , item.getTransactionTypeName());
        }
        return map;
    }
}