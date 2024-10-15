package com.gstock.kis.config;

import com.gstock.kis.KisConstants;
import com.gstock.kis.service.AccessTokenManager;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.gstock.kis.KisConstants.APP_KEY;
import static com.gstock.kis.KisConstants.APP_SECRET_KEY;

@Configuration
@Slf4j
public class FeignConfig {

    @Autowired
    private AccessTokenManager accessTokenManager;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                String accessToken = accessTokenManager.getAccessToken();
                template.header("content-type", "application/json; charset=utf-8");
                template.header("authorization", "Bearer " + accessToken);
                template.header("appkey", APP_KEY);
                template.header("appsecret", APP_SECRET_KEY);
                template.header("custtype","P");
                log.error(template.headers().toString());
                }
        };
    }

}
