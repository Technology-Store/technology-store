package com.winnguyen1905.technologystore.converter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.winnguyen1905.technologystore.entity.PermissionEntity;
import com.winnguyen1905.technologystore.model.request.PermissionSearchRequest;
import com.winnguyen1905.technologystore.repository.specification.QuerySpecification;


@Component
public class PermissionConverter {
    public Specification<PermissionEntity> toPermissionSpec(PermissionSearchRequest permissionSearchRequest) {
        Field[] fields = permissionSearchRequest.getClass().getDeclaredFields();
        List<Specification<PermissionEntity>> specList = new ArrayList<>();
        java.util.Arrays.asList(fields).forEach(field -> {
            try {
                String fieldName = field.getName();
                field.setAccessible(true);
                Object value = field.get(permissionSearchRequest);
                if(value.getClass().getName().equals("java.lang.String"))
                specList.add(QuerySpecification.isValueLike((String) value, fieldName, null));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return Specification.allOf(specList);
    }
}