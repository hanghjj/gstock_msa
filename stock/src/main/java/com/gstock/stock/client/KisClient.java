package com.gstock.stock.client;

import com.gstock.stock.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "KisClient", url = "${feign.client.kisClient.url}", configuration = FeignConfig.class)
public interface KisClient {
    @GetMapping("/info/{srtnCd}")
    String getStockInfo(@PathVariable(value = "srtnCd") String srtnCd);
    @GetMapping("/price/{srtnCd}")
    String getStockPrice(@PathVariable(value = "srtnCd") String srtnCd);
}
