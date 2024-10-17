package com.gstock.portfolio.clients;


import com.gstock.portfolio.config.FeignConfig;
import com.gstock.portfolio.dto.CoinDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CoinClient", url = "${feign.client.CoinClient.url}", configuration = FeignConfig.class)
public interface CoinClient {
    //코인정보 조회
    @GetMapping("/inquiry/{ticker}")
    CoinDto getCoinInfo(@PathVariable(value = "ticker") String ticker);
}