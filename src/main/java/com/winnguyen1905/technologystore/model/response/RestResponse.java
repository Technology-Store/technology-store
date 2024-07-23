package com.winnguyen1905.technologystore.model.response;

import lombok.*;

@Getter
@Setter
@Builder
public class RestResponse<T> {
    private int statusCode;
    private String error;
    private Object message;
    private T Data;
}