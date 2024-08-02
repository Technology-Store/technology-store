package com.winnguyen1905.technologystore.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.*;

@Getter
@Setter
@Builder
@JsonInclude(value = Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RestResponse<T> {
    private int statusCode;
    private String error;
    private Object message;
    private T Data;
}