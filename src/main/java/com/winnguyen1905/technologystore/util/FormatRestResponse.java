package com.winnguyen1905.technologystore.util;

import org.springframework.http.server.ServerHttpResponse;

import com.winnguyen1905.technologystore.model.response.RestResponse;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class FormatRestResponse implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    @Nullable
    public Object beforeBodyWrite(
        @Nullable Object body, 
        MethodParameter returnType, 
        MediaType selectedContentType,
        Class selectedConverterType, 
        ServerHttpRequest request, 
        ServerHttpResponse response
    ) {
        HttpServletResponse servletResponse = ((ServletServerHttpResponse) response).getServletResponse();
        int statusCode = servletResponse.getStatus();

        RestResponse<Object> restResponse = RestResponse.builder().statusCode(statusCode).build();

        if(statusCode > 399) {
            restResponse.setError("Call api failed");
            restResponse.setMessage(body);
        } else {
            restResponse.setData(body);
            restResponse.setMessage("Success");
        }
        
        return restResponse;
    }
    
}