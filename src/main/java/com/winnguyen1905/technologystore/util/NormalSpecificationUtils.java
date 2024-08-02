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
                String fieldName = StringUtils.convertCamelToSnake(field.getName());
                field.setAccessible(true);
                Object value = field.get(tSearchRequest);
                if(value == null || fieldName.indexOf("_id") != -1) return;
                else if(fieldName.endsWith("_from"))
                    specList.add(CustomSpecification.isGreaterThanOrEqual((Integer) value, fieldName.substring(0, fieldName.length() - 5), null));
                else if(fieldName.endsWith("_to"))
                    specList.add(CustomSpecification.isLessThanOrEqual((Integer) value, fieldName.substring(0, fieldName.length() - 3), null));
                else if(value.getClass().getName().equals("java.lang.String"))
                    specList.add(CustomSpecification.isValueLike((String) value, fieldName, null));
                else if(value instanceof Number)
                    specList.add(CustomSpecification.isEqualValue((Integer) value, fieldName, null));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return specList;
    }
}