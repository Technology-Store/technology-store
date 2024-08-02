package com.winnguyen1905.technologystore.core.logging;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

@Component
public class RestTemplateFilter implements ClientHttpRequestInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateFilter.class);
    // Which is called before an HTTP request is sent and after the HTTP response is received.
    @Override
    @SuppressWarnings("null")
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        // In this method, the HTTP request is logged, including the HTTP method, URI and request body (if any).
        LOGGER.info("RestTemplate does a http/s to - {} with HTTP Method : {}", request.getURI(), request.getMethod());

        ClientHttpResponse response = execution.execute(request, body);

        // HTTP response, including status code, status text and response body (if any). Log messages are logged using a logging library such as SLF4J.
        if (response.getStatusCode().is4xxClientError() || response.getStatusCode()
            .is5xxServerError()) {
            LOGGER.error("RestTemplate received a bad response from : {} - with response status : {} and body : {} ",
            request.getURI(), response.getStatusCode(), new String(response.getBody().readAllBytes(), StandardCharsets.UTF_8));
        } else {
            LOGGER.info("RestTemplate received a good response from : {}- with response status {}",
            request.getURI(),
            response.getStatusCode());
        }
        return response;
    }
}