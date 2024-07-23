package com.winnguyen1905.technologystore.utils;

import java.security.SecureRandom;
import java.text.Normalizer;
import java.util.Base64;
import java.util.Locale;
import java.util.regex.Pattern;

import com.winnguyen1905.technologystore.entity.ProductEntity;

public class SlugUtils {

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    public static String slugGenerator(ProductEntity productEntity) {
        String
            nowhitespace = WHITESPACE.matcher(productEntity.getName()).replaceAll("-"), 
            normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD),
            slug = NONLATIN.matcher(normalized).replaceAll("") + '-';
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);

        return slug.toLowerCase(Locale.ENGLISH) + base64Encoder.encodeToString(randomBytes);
    }

}
