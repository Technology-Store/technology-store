package com.winnguyen1905.technologystore.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application-dev.properties")
public class CookieUtils {
    @Value("${techstore.jwt.refresh_token-validity-in-seconds}")
    private String jwtRefreshTokenExpiration;

    public ResponseCookie deleteCookie(String name) {
        return ResponseCookie
            .from(name, "")
            .httpOnly(true)
            .secure(true)
            .path("/")
            .maxAge(0)
            .build();
    }
    
    public ResponseCookie createCookie(String name, String refreshToken) {
        return ResponseCookie
            .from(name, refreshToken)
            .httpOnly(true)
            .secure(true)
            .path("/")
            .maxAge(Long.parseLong(jwtRefreshTokenExpiration))
            .build();
    }
}