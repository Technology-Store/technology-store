package com.winnguyen1905.technologystore.util;

import java.lang.reflect.Field;
import java.util.Arrays;

public class OverwriteUtils {
    /**
     * @param T overwritterObject
     * @param F beModifiedObject
     * @return T beModifiedObject Object after be over written
     * T and F can be same type
     */
    public static <T, F> F overwrireObject(T overwritterObject, F beModifiedObject) {
        Field[] fields = overwritterObject.getClass().getDeclaredFields();
            Arrays.asList(fields).forEach(field -> {
                String fieldName = field.getName();
                try {
                    Field fieldOfBeModifiedObject = beModifiedObject.getClass().getDeclaredField(fieldName);
                    field.setAccessible(true);
                    fieldOfBeModifiedObject.setAccessible(true);
                    if(field.get(overwritterObject) != null) fieldOfBeModifiedObject.set(beModifiedObject, field.get(overwritterObject));
                } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
        return beModifiedObject;
    }
}