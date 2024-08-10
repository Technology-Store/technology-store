package com.winnguyen1905.technologystore.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.winnguyen1905.technologystore.util.annotation.handler.FutureInstantValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = FutureInstantValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FutureInstant {
    String message() default "Time must be in the future";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}