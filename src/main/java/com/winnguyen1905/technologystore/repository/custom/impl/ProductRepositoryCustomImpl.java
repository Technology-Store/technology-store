package com.winnguyen1905.technologystore.repository.custom.impl;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.winnguyen1905.technologystore.builder.ProductSearchBuilder;
import com.winnguyen1905.technologystore.entity.ProductEntity;
import com.winnguyen1905.technologystore.repository.custom.ProductRepositoryCustom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Primary
@Repository
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    @Autowired
    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;

    public String joinTable(ProductSearchBuilder productSearchBuilder) {
        StringBuilder result = new StringBuilder(" select * from product ");
        List<String> typeCode = productSearchBuilder.getTypeCode();
        if(typeCode != null && typeCode.size() > 0) {
            String join = typeCode.stream().
                map(item -> (" right join " + item.toString() + " on " + item.toString() + "." + item.toString() + "_id = product.id ")).
                collect(Collectors.joining(" "));
            result.append(join);
        }
        return result.toString();
    }

    public static Boolean fieldNameChecking(String fieldName) {
        return 
            fieldName.equals("priceFrom") || 
            fieldName.equals("typeCode") || 
            fieldName.startsWith("priceTo") || 
            fieldName.startsWith("more");
    }

    public static String conditionToString(Field field, ProductSearchBuilder productSearchBuilder) {
        String result = "";
        try {
            field.setAccessible(true);
            Object x = field.get(productSearchBuilder);
            String fieldName = field.getName();
            if(x == null || fieldNameChecking(fieldName)) result = "";
            else if(x.getClass().getName().equals("java.lang.String")) result = " and p." + field.getName() + " like '%" + x.toString() + "%' "; 
            else result = " and p." + field.getName() + " = " + x.toString();
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void queryNormal(StringBuilder sql, ProductSearchBuilder productSearchBuilder) {
        Field[] fields = productSearchBuilder.getClass().getDeclaredFields();
        String normalQuery = Arrays.asList(fields).
            stream().
            map(item -> conditionToString(item, productSearchBuilder)).
            collect(Collectors.joining(""));
        sql.append(normalQuery);
    }

    public static void querySpecial(StringBuilder sql, ProductSearchBuilder productSearchBuilder) {

    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<ProductEntity> findAll(ProductSearchBuilder productSearchBuilder) {
        StringBuilder sql = new StringBuilder("select p.* from ( " + joinTable(productSearchBuilder) + " ) as p where 1 = 1 ");
        queryNormal(sql, productSearchBuilder);
        System.out.println(sql);
        Query query = entityManager.createNativeQuery(sql.toString(), ProductEntity.class);
        return query.getResultList();
    }
    
}