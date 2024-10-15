package com.gstock.core.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "Gstock Core API",
                description = "Gstock 코어 API 문서",
                version = "v1"))
@RequiredArgsConstructor
@Configuration
public class SwaggerConfiguration {
    @Bean
    public GroupedOpenApi OpenApi() {
        String[] paths = {"/**"};

        return GroupedOpenApi.builder()
                .group("Gstock Core API")
                .pathsToMatch(paths)
                .build();
    }
}