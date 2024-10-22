package com.gstock.stock.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gstock.gutils.exception.CustomException;
import com.gstock.stock.dto.SrtnCdDto;
import com.gstock.stock.dto.StockDto;
import com.gstock.stock.dto.StockPriceDto;
import com.gstock.stock.entity.Stock;
import com.gstock.stock.objects.CustomObjectMapper;
import com.gstock.stock.service.StockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.gstock.stock.StockConstants.DEFAULT_ERR_MSG;

@Controller
@RequestMapping("/api/stock")
@AllArgsConstructor
@Slf4j
@Tag(name = "Stock", description = "주식 관련 API")
@CrossOrigin(origins = "http://localhost:8080")
public class StockController {
    private final StockService stockService;
    private final CustomObjectMapper mapper;

    @GetMapping("/getstock")
    @Operation(summary = "주식정보 조회(DB)", description = "DB에 저장된 주식 정보 조회")
    @Tag(name = "Stock")
    public ResponseEntity<Stock> getStock(@Parameter(name = "srtnCd", description = "종목코드") @RequestParam(value = "srtnCd") String srtnCd) {
        return ResponseEntity.status(HttpStatus.OK).body(stockService.getStock(srtnCd));
    }

    @GetMapping("/code/{stckNm}")
    @Operation(summary = "종목코드 조회", description = "종목명, 종목코드 조회")
    @Tag(name = "Stock")
    public ResponseEntity<List<SrtnCdDto>> getSrtnCd(@Parameter(name = "stckNm", description = "종목명") @PathVariable("stckNm") String stckNm) throws JsonProcessingException {
        List<SrtnCdDto> modelList = new ArrayList<>();
        String url = "https://m.stock.naver.com/api/json/search/searchListJson.nhn?keyword=" + stckNm;
        Document doc = null;
        try {
            doc = Jsoup.connect(url).header("content-type", "application/json;charset=UTF-8")
                    .header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                    .header("accept-encoding", "gzip, deflate, br")
                    .header("accept-language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
                    .ignoreContentType(true).get();
        } catch (IOException e) {
            throw new CustomException("stock.search.error");
        }
        String stockJsonList;
        try {
            stockJsonList = doc.text();
        } catch (NullPointerException e) {
            throw new CustomException(DEFAULT_ERR_MSG);
        }

        JSONArray array = new JSONObject(stockJsonList).getJSONObject("result").getJSONArray("d");
        modelList = Arrays.stream(mapper.readValue(array.toString(), SrtnCdDto[].class))
                          .filter(s -> {
                                        return s.getNm().contains(stckNm);})
                          .toList();
        if (modelList.size() == 0) {
            throw new CustomException("stock.search.fail");
        }
        return ResponseEntity.status(HttpStatus.OK).body(modelList);
    }


    @GetMapping("/kis/info/{srtnCd}")
    @Operation(summary = "한국투자증권 주식 기본 정보 조회[국내/해외]", description = "KIS 주식 기본 정보 조회")
    @Tag(name = "Stock")
    public ResponseEntity<StockDto> getKisStockInfo(@Parameter(name = "srtnCd", description = "종목코드") @PathVariable("srtnCd") String srtnCd) throws JsonProcessingException {
        StockDto stockDto = stockService.getStockInfoFromKis(srtnCd);
        StockPriceDto stockPriceDto = stockService.getStockPriceFromKis(stockDto);
        stockService.saveStock(stockDto);
        stockService.saveStock(stockPriceDto);
        BeanUtils.copyProperties(stockPriceDto,stockDto);
        return ResponseEntity.status(200).body(stockDto);
    }
}
