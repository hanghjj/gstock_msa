package com.gstock.gateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class webConfig implements WebFluxConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(getAllowOrigins()) // 허용할 도메인
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    private String[] getAllowOrigins(){
        List<String> allowedOriginList = new ArrayList<>();

        int startPort = 8081;
        int endPort = 8085;
        for (int port = startPort; port <= endPort; port++) {
            allowedOriginList.add("http://localhost:" + port);
            allowedOriginList.add("https://localhost:" + port);
            allowedOriginList.add("http://127.0.0.1:" + port);
            allowedOriginList.add("https://127.0.0.1:" + port);
        }

        return allowedOriginList.toArray(new String[0]);
    }
}
