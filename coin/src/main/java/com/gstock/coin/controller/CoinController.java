package com.gstock.coin.controller;

import com.gstock.coin.dto.CoinDto;
import com.gstock.coin.service.CoinService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/coin")
@AllArgsConstructor
@Slf4j
@Tag(name = "Coin", description = "코인 관련 API")
public class CoinController {
    private final CoinService coinService;
    @GetMapping("/inquiry/{ticker}")
    @Operation(summary = "코인정보 조회", description = "코인명, 현재시세 조회")
    @Tag(name = "Coin")
    public ResponseEntity<CoinDto> getCoinInfo(@Parameter(name = "ticker", description = "티커")  @PathVariable(value = "ticker") String ticker) {
        CoinDto result = coinService.getCoinPrice(ticker);
        CoinDto info = coinService.getCoinInfo(ticker);
        result.setItmNm(info.getItmNm());
        coinService.saveCoin(result);
        return ResponseEntity.ok(result);
    }
}
