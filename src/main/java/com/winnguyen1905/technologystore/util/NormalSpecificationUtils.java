package com.winnguyen1905.technologystore.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.winnguyen1905.technologystore.repository.specification.CustomSpecification;

public class NormalSpecificationUtils<D> {
    public static <T, D> List<Specification<D>> toNormalSpec(T tSearchRequest) {
        Field[] fields = tSearchRequest.getClass().getDeclaredFields();
        List<Specification<D>> specList = new ArrayList<>();
        Arrays.asList(fields).forEach(field -> {
            try {
                String fieldName = field.getName();
                field.setAccessible(true);
                Object value = field.get(tSearchRequest);
                System.out.println(value.getClass().getName());
                if(value == null || fieldName.indexOf("Id") != -1) return;
                
                if(value instanceof Boolean bl) {
                    if(bl == true) specList.add(CustomSpecification.isTrue((Boolean) value, fieldName, null));
                    else specList.add(CustomSpecification.isFalse((Boolean) value, fieldName, null));
                    return;
                }
                if(value instanceof String str) {
                    specList.add(CustomSpecification.isValueLike((String) str, fieldName, null));
                    return;
                }
                if(value instanceof Number num) {
                    if(fieldName.endsWith("From"))  specList.add(CustomSpecification.isGreaterThanOrEqual((Double) num, fieldName.substring(0, fieldName.length() - 4), null));
                    else if(fieldName.endsWith("To")) specList.add(CustomSpecification.isLessThanOrEqual((Double) num, fieldName.substring(0, fieldName.length() - 2), null));
                    specList.add(CustomSpecification.isEqualValue((Integer) num, fieldName, null));
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return specList;
    }
}