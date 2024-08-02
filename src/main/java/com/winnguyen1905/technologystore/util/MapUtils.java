package com.winnguyen1905.technologystore.util;

import java.util.Map;

public class MapUtils {
    public static <T> T getObject(Map<String, Object> params, String key, Class<T> tClass) {
        Object obj = params.getOrDefault(key, null);
        if(obj != null && !obj.equals("")) {
            if(tClass.getTypeName().equals("java.lang.Long")) return tClass.cast(Long.valueOf(obj.toString()));
            else if(tClass.getTypeName().equals("java.lang.Integer")) return tClass.cast(Integer.valueOf(obj.toString()));
            else if(tClass.getTypeName().equals("java.lang.String")) return tClass.cast(obj.toString());
        }
        return null;
    }
}