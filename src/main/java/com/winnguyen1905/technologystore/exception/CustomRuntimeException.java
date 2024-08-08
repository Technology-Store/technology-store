package com.winnguyen1905.technologystore.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomRuntimeException extends RuntimeException {
    private String message;
    private int statusCode;
    private Object error;

    public CustomRuntimeException(String message) {
        this.message = message;
        this.statusCode = 400;
        this.error = "Exception occurs";
    }

    public CustomRuntimeException(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
        this.error = "Exception occurs";
    }
}