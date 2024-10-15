package com.gstock.kis.service;

import com.gstock.kis.KisConstants;
import com.gstock.kis.client.TokenClient;
import com.gstock.kis.entity.OauthInfo;
import com.gstock.kis.entity.TokenInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccessTokenManager {

    private final TokenClient tokenClient;
    public static String ACCESS_TOKEN;
    @Autowired
    public AccessTokenManager(TokenClient tokenClient) {
        this.tokenClient = tokenClient;
    }

    public String getAccessToken() {
        if (ACCESS_TOKEN == null) {
            ACCESS_TOKEN = generateAccessToken();
            System.out.println("generate ACCESS_TOKEN: " + ACCESS_TOKEN);
        }

        return ACCESS_TOKEN;
    }


    public String generateAccessToken() {
        OauthInfo bodyOauthInfo = new OauthInfo();
        bodyOauthInfo.setAppkey(KisConstants.APP_KEY);
        bodyOauthInfo.setAppsecret(KisConstants.APP_SECRET_KEY);
        bodyOauthInfo.setGrant_type(KisConstants.GRANT_INFO);
        TokenInfo tokenInfo = tokenClient.getAccessToken(null, bodyOauthInfo);
        if (tokenInfo == null) {
            throw new RuntimeException("액세스 토큰을 가져올 수 없습니다.");
        }
        ACCESS_TOKEN = tokenInfo.getAccess_token();
        return ACCESS_TOKEN;
    }

}