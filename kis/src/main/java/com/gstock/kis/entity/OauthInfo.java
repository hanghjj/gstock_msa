package com.gstock.kis.entity;

import lombok.Data;

@Data
public class OauthInfo {
    private String grant_type ;
    private String appkey;
    private String appsecret;
}
