package com.gstock.kis.service;

import com.gstock.gutils.exception.CustomException;
import com.gstock.gutils.util.StringUtils;
import com.gstock.kis.KisConstants;
import com.gstock.kis.client.KisClient;
import feign.FeignException;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static com.gstock.kis.KisConstants.*;

@Service
@Slf4j
public class KisService {
    @Autowired
    private ApiAction apiAction;

    @Autowired
    private KisClient kisClient;

    public String getStockInfoFromKis(String id, @Nullable String marketCode) {
        String productTypeCode = StringUtils.isDigit(id) ? DOMESTIC : Optional.ofNullable(marketCode).orElse(NASDAQ);
        String result = kisClient.getStockInfo(TOTAL_STOCK_INFO_KEY, id, productTypeCode);
        String msgCd = new JSONObject(result).get("msg_cd").toString();
        if(StringUtils.equals(msgCd,KIS_MSG_CD_FAIL) && StringUtils.equals(productTypeCode,NASDAQ)){
            result = kisClient.getStockInfo(TOTAL_STOCK_INFO_KEY, id, AMEX);
        }
        return result;
    }

    public String getStockPriceFromKis(String srtnCd) {
        if (StringUtils.isDigit(srtnCd)) { //숫자코드 -> 국내주식
            try {
                return kisClient.getDomesticStockPrice(DOMESTIC_STOCK_PRICE_KEY, "J", srtnCd);
            } catch (FeignException e) {
                log.error("Error occurred: {} - Status code: {}", e.getMessage(), e.status());
                throw new CustomException("common.error", Collections.singletonList( "Error occurred: " + e.getMessage() + " - Status code: " + e.status()));
            }
        } else {
            String result = "";
            try { //나스닥 기본조회
                result = kisClient.getOverseasStockPrice(OVERSEAS_STOCK_PRICE_KEY, "", NAS, srtnCd);
                JSONObject jsonObject = new JSONObject(result);
                String ticker = Optional.ofNullable(new JSONObject(jsonObject.get("output").toString()).get("rsym")).orElse("").toString();
                if (StringUtils.isEmpty(ticker)) {
                    result = kisClient.getOverseasStockPrice(OVERSEAS_STOCK_PRICE_KEY, "", AME, srtnCd);
                }
            } catch (JSONException e) {
                throw new CustomException("common.error", Collections.singletonList(e.getMessage()));
            }
            return result;
        }
    }
}
