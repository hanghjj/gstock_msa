package com.gstock.portfolio.clients;


import com.gstock.portfolio.config.FeignConfig;
import com.gstock.portfolio.dto.StockDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "StockClient", url = "${feign.client.StockClient.url}", configuration = FeignConfig.class)
public interface StockClient {
    @GetMapping("/kis/info/{srtnCd}")
    StockDto getStockInfo(@PathVariable("srtnCd") String srtnCd);
}
