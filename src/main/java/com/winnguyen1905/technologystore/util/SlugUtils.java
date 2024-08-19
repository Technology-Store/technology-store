package com.winnguyen1905.technologystore.util;

import java.security.SecureRandom;
import java.text.Normalizer;
import java.util.Base64;
import java.util.Locale;
import java.util.regex.Pattern;

import com.winnguyen1905.technologystore.entity.ProductEntity;

public class SlugUtils {

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    public static String slugGenerator(ProductEntity productEntity) {
        String
            nowhitespace = WHITESPACE.matcher(productEntity.getName()).replaceAll("-"), 
            normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD),
            slug = NONLATIN.matcher(normalized).replaceAll("") + '-';
        return slug.toLowerCase(Locale.ENGLISH) + System.currentTimeMillis();
    }

}