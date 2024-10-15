package com.gstock.kis.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import static com.gstock.kis.KisConstants.APP_KEY;
import static com.gstock.kis.KisConstants.APP_SECRET_KEY;

@Component
public class ApiAction {
    @Autowired
    private AccessTokenManager accessTokenManager;
    public JSONObject get(String target, String action) {
     try {
         HttpHeaders headers = new HttpHeaders();
         headers.set("content-type", "application/json; charset=utf-8");
         headers.set("authorization", "Bearer " + accessTokenManager.getAccessToken());
         headers.set("appkey", APP_KEY);
         headers.set("appsecret", APP_SECRET_KEY);
         headers.set("tr_id", action);
         headers.set("custtype","P");

         HttpEntity<String> entity = new HttpEntity<String>("", headers);

         RestTemplate restTemplate = new RestTemplate();
         ResponseEntity<String> respEntity = restTemplate.exchange(target, HttpMethod.GET, entity, String.class);

         return new JSONObject(respEntity.getBody());
     }catch (Exception e){
         e.printStackTrace();
     }
     return new JSONObject();
   }
}
