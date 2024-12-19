package com.josunhotel.monitoring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public static final String ALLOWED_METHOD_NAMES = "GET";

    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedMethods(ALLOWED_METHOD_NAMES)
                .allowedOrigins("*")
                .allowedOriginPatterns("*")
                .exposedHeaders(HttpHeaders.LOCATION);
    }
}
