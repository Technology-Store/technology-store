package com.winnguyen1905.technologystore.common;

import java.util.Map;
import java.util.TreeMap;

public enum AssignmentRole {
    MANAGER("manager"),
    SECURITY("security"),
    MAINTENANCE("maintenance");

    private final String assignmentRole;

    AssignmentRole(String assignmentRole) {
        this.assignmentRole = assignmentRole;
    }

    public String getAssignmentRole() {
        return this.assignmentRole;
    }

    public static Map<String,String> type() {
        Map<String, String> map = new TreeMap<>();
        for(AssignmentRole item : AssignmentRole.values()){
            map.put(item.toString() , item.getAssignmentRole());
        }
        return map;
    }
}