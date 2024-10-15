package com.gstock.kis.client;

import com.gstock.kis.entity.OauthInfo;
import com.gstock.kis.entity.TokenInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "tokenClient", url = "${oauth2.token.url}")
public interface TokenClient {
    @PostMapping(consumes = "application/json", produces = "application/json")
    TokenInfo getAccessToken(
            @RequestHeader(value = "Content-Type", defaultValue = "application/json ") String contentType,
            @RequestBody OauthInfo bodyOauthInfo);
}