package com.gstock.kis.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.gstock.gutils.exception.CustomException;
import com.gstock.kis.service.AccessTokenManager;
import com.gstock.kis.service.KisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/kis")
public class KisController {
    @Autowired
    private AccessTokenManager accessTokenManager;

    @Autowired
    private KisService kisService;

    @GetMapping("/info/{srtnCd}")
    @Operation(summary = "한국투자증권 주식 기본 정보 조회[국내/해외]", description = "KIS 주식 기본 정보 조회")
    @Tag(name = "Stock")
    public ResponseEntity<String> getKisStockInfo(@Parameter(name = "srtnCd", description = "종목코드") @PathVariable("srtnCd") String srtnCd) {
        String stockInfoJson = kisService.getStockInfoFromKis(srtnCd, null);  //주식 기본 정보 Json Return으로 수정
        return ResponseEntity.status(200).body(stockInfoJson);
    }
    @GetMapping("/price/{srtnCd}")
    @Operation(summary = "한국투자증권 주식 시세 조회[국내/해외]", description = "KIS 주식 시세 조회")
    @Tag(name = "Stock")
    public ResponseEntity<String> getKisStockPrice(@Parameter(name = "srtnCd", description = "종목코드") @PathVariable("srtnCd") String srtnCd) throws JsonProcessingException {
        String stockPriceJson = kisService.getStockPriceFromKis(srtnCd); //주식 시세 정보 Json Return으로 수정
        return ResponseEntity.status(200).body(stockPriceJson);
    }
}