package com.winnguyen1905.technologystore.core.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateFilter.class);

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void restController() {}

    @Around("restController()")
    public Object logRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        LOGGER.info("\n---::Request method: {}", joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        LOGGER.info("\n---::Response: {}", result.toString());
        return result;
    }
}