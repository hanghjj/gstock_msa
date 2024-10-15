package com.gstock.kis.client;

import com.gstock.kis.config.FeignConfig;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "CoreClient", url = "${feign.client.coreClient.url}", configuration = FeignConfig.class)
public interface CoreClient {
    @GetMapping("/msg/search")
    String getMessageCode(@Parameter(name = "code", description = "메세지코드") @RequestParam(value = "code") String code);
}
