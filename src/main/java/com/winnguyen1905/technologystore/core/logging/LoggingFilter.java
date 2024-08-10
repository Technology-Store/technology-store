package com.winnguyen1905.technologystore.core.logging;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

//component that allows you to Integerercept the incoming and outgoing HTTP messages at the level of the servlet container.
@Component
public class LoggingFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code goes here
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var req = (HttpServletRequest) request;
        LOGGER.info("\n---::Logging request: " + req.getMethod() + " " + req.getRequestURI());
        chain.doFilter(request, response);
        LOGGER.info("\n---::Logging response: " + response.getContentType());
    }

    @Override
    public void destroy() {
        // Cleanup code goes here
    }
}