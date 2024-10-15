package com.gstock.kis.client;

import com.gstock.kis.KisConstants;
import com.gstock.kis.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "KisClient", url = "${feign.client.kisClient.url}", configuration = FeignConfig.class)
public interface KisClient {
    @GetMapping("/uapi/domestic-stock/v1/quotations/inquire-price")
    String getDomesticStockPrice(@RequestHeader(value = "tr_id") String key,
                                   @RequestParam(value = "fid_cond_mrkt_div_code", defaultValue = "J") String mrktCode,
                                   @RequestParam(value = "fid_input_iscd") String strnCd);

    @GetMapping(value = "/uapi/overseas-price/v1/quotations/price", consumes = "application/json", produces = "application/json")
    String getOverseasStockPrice(@RequestHeader(value = "tr_id") String key,
                                     @RequestParam(value = "auth", defaultValue = "") String auth,
                                     @RequestParam(value = "excd") String excd,
                                     @RequestParam(value = "symb") String strnCd);

    @GetMapping(value = KisConstants.DOMESTIC_STOCK_INFO_URL, consumes = "application/json", produces = "application/json")
    String getStockInfo(@RequestHeader(value = "tr_id") String key,
                        @RequestParam(value = "pdno") String pdno,
                        @RequestParam(value = "prdt_type_cd") String prdtTypeCd);


}
