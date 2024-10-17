package com.gstock.portfolio.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gstock.gutils.util.DateTimeUtils;
import com.gstock.portfolio.dto.PortfolioDto;
import com.gstock.portfolio.service.PortfolioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@AllArgsConstructor
@Tag(name = "Portfolio", description = "포트폴리오 관련 API")
@RequestMapping("/api/portfolio")
public class PortfolioController {
    private final PortfolioService service;

    @GetMapping("/insert/detail")
    @Operation(summary = "포트폴리오 종목 추가", description = "포트폴리오 종목 추가")
    @Tag(name = "Portfolio")
    public ResponseEntity<PortfolioDto> insertPortfolioDetails(
            @Parameter(name = "userId", description = "사용자ID") @RequestParam(name = "userId", required = true) String userId,
            @Parameter(name = "portfolioId", description = "포트폴리오ID") @RequestParam(name = "portfolioId", required = true) String portfolioId,
            @Parameter(name = "ticker", description = "티커") @RequestParam(name = "ticker", required = false) String ticker,
            @Parameter(name = "asstSeCd", description = "자산구분코드",
                    examples = {
                            @ExampleObject(name = "Stock", value = "S"),
                            @ExampleObject(name = "Coin", value = "C"),
                            @ExampleObject(name = "KRW", value = "K"),
                            @ExampleObject(name = "USD", value = "U")
                    }
            ) @RequestParam(name = "asstSeCd", required = false) String asstSeCd,
            @Parameter(name = "qty", description = "보유수량") @RequestParam(name = "qty", required = false) Double qty,
            @Parameter(name = "avgPcsPce", description = "매수평균가") @RequestParam(name = "avgPcsPce", required = false) Double avgPcsPce) throws JsonProcessingException {
        PortfolioDto dto = new PortfolioDto(userId, portfolioId, asstSeCd, ticker, qty, avgPcsPce);
        service.insertPortfolio(dto);
        dto = service.getLatestPrice(dto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/get/detail")
    @Operation(summary = "포트폴리오 세부정보 조회", description = "포트폴리오 세부정보 조회")
    @Tag(name = "Portfolio")
    public ResponseEntity<List<PortfolioDto>> getPortfolioDetails(
            @Parameter(name = "userId", description = "사용자ID") @RequestParam(name = "userId", required = true) String userId,
            @Parameter(name = "portfolioId", description = "포트폴리오ID") @RequestParam(name = "portfolioId", required = true) String portfolioId
    ) {

        //userId 검증 필요
        List<PortfolioDto> resultList = service.getPortfolioDetailList(portfolioId);
        resultList.forEach(dto ->{
            dto.setUserId(userId);
            dto.setUpYmd(DateTimeUtils.getDateFormat("yyyyMMdd"));
        });
        return ResponseEntity.ok(resultList);
    }

    @GetMapping("/get/list")
    @Operation(summary = "포트폴리오 목록 조회", description = "포트폴리오 목록 조회")
    @Tag(name = "Portfolio")
    public ResponseEntity<List<PortfolioDto>> getPortfolioDetails(
            @Parameter(name = "userId", description = "사용자ID") @RequestParam(name = "userId", required = true) String userId
    ) {
        return ResponseEntity.ok(service.getPortfolioList(userId));
    }

    @GetMapping("/delete/portfolio")
    @Operation(summary = "포트폴리오 목록 삭제", description = "포트폴리오 목록 삭제")
    @Tag(name = "Portfolio")
    public ResponseEntity<PortfolioDto> deletePortfolio(
            @Parameter(name = "userId", description = "사용자ID") @RequestParam(name = "userId", required = true) String userId,
            @Parameter(name = "portfolioId", description = "포트폴리오ID") @RequestParam(name = "portfolioId", required = true) String portfolioId
    ){
        service.deletePortfolio(userId,portfolioId);
        return ResponseEntity.ok(new PortfolioDto(userId,portfolioId));
    }

    @GetMapping("/delete/details")
    @Operation(summary = "포트폴리오 세부 항목 삭제", description = "포트폴리오 세부 항목 삭제")
    @Tag(name = "Portfolio")
    public ResponseEntity<PortfolioDto> deletePortfolio(
            @Parameter(name = "userId", description = "사용자ID") @RequestParam(name = "userId", required = true) String userId,
            @Parameter(name = "portfolioId", description = "포트폴리오ID") @RequestParam(name = "portfolioId", required = true) String portfolioId,
            @Parameter(name = "ticker", description = "티커") @RequestParam(name = "ticker", required = true) String ticker
    ){
        service.deletePortfolioDetails(userId,portfolioId,ticker);
        return ResponseEntity.ok(new PortfolioDto(userId,portfolioId,ticker));
    }

    @GetMapping("/simul/scale")
    @Operation(summary = "물타기 계산기", description = "물타기 게산기")
    @Tag(name = "Portfolio")
    public ResponseEntity<PortfolioDto> getScaleTradingSimulation(
            @Parameter(name = "ticker", description = "티커") @RequestParam(name = "ticker", required = true) String ticker,
            @Parameter(name = "qty", description = "보유수량") @RequestParam(name = "qty", required = true) Double qty,
            @Parameter(name = "avgPcsPce", description = "매수평균가") @RequestParam(name = "avgPcsPce", required = true) Double avgPcsPce,
            @Parameter(name = "addQty", description = "추가수량") @RequestParam(name = "addQty", required = true) Double addQty,
            @Parameter(name = "pcsPce", description = "매수가") @RequestParam(name = "pcsPce", required = false) Double pcsPce
    ) throws JsonProcessingException {
        PortfolioDto input = new PortfolioDto(ticker,qty,avgPcsPce);
        return ResponseEntity.ok(service.scaleTradingSimulation(input,addQty,pcsPce));
    }
}
