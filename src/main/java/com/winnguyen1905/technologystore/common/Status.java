package com.winnguyen1905.technologystore.common;

import java.util.Map;
import java.util.TreeMap;

public enum Status {
    PROCESSING ("processing"),
    PROCESSED ("processed"),
    NOPROCESS ("noprocess");

    private final String statusName;

    Status(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }

    public static Map<String,String> type() {
        Map<String, String> listStatus = new TreeMap<>();
        for(Status item : Status.values()){
            listStatus.put(item.toString() , item.statusName);
        }
        return listStatus;
    }
}