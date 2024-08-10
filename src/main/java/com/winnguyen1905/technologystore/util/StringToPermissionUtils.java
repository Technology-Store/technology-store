package com.winnguyen1905.technologystore.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.winnguyen1905.technologystore.model.dto.PermissionDTO;

public class StringToPermissionUtils {
    public static PermissionDTO toPermission(String stringPermission) {

        String trimmedstringPermission = stringPermission.replaceAll("^\"|\"$", "").replaceAll("^\\{|\\}$", "");

        String[] keyValuePairs = trimmedstringPermission.split(",(?![^\\{\\[]*[\\]\\}])");

        Map<String, String> map = new HashMap<>();
        for (String pair : keyValuePairs) {
            String[] entry = pair.split("=", 2);
            if (entry.length == 2) {
                map.put(entry[0].trim(), entry[1].trim());
            }
        }

        PermissionDTO dto = new PermissionDTO();
        dto.setName(map.get("name"));
        dto.setCode(map.get("code"));
        dto.setApiPath(map.get("apiPath"));
        dto.setMethod(map.get("method"));
        dto.setModule(map.get("module"));
        dto.setId(UUID.fromString(map.get("id")));
        return dto;
    }
}