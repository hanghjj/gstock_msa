package com.gstock.coin.objects;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomObjectMapper extends ObjectMapper{


    public CustomObjectMapper() {
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    }
}
