package com.gstock.coin.client;

import com.gstock.coin.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CoinOneClient", url = "${feign.client.CoinOneClient.url}", configuration = FeignConfig.class)
public interface CoinOneClient {
    @GetMapping("/ticker_new/KRW/{ticker}")
    String getCoinPrice(@PathVariable(value = "ticker") String ticker);

    @GetMapping("/currencies/{ticker}")
    String getCoinInfo(@PathVariable(value = "ticker") String ticker);

}
