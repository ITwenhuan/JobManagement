package com.example.job.jobservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 解决跨域
 */
@Configuration
public class MyCorsConfig implements WebMvcConfigurer {

    @Value(value = "${params.CROS_MAPPING}")
    private String mapping;
    @Value(value = "${params.CROS_ALLOWED_ORIGINS}")
    private String[] allowedOrigins;
    @Value(value = "${params.CROS_ALLOWED_METHOD}")
    private String[] allowedMethods;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(mapping).allowedOrigins(allowedOrigins).allowedMethods(allowedMethods).allowCredentials(true);
    }
}
