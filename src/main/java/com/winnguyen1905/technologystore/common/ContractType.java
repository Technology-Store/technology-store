package com.winnguyen1905.technologystore.common;

import java.util.Map;
import java.util.TreeMap;

public enum ContractType {
    RENT("rent"),
    BUY("buy");

    private final String contractType;

    ContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getContractType() {
        return contractType;
    }

    public static Map<String,String> type() {
        Map<String, String> listContractType = new TreeMap<>();
        for(ContractType item : ContractType.values()){
            listContractType.put(item.toString() , item.getContractType());
        }
        return listContractType;
    }
}