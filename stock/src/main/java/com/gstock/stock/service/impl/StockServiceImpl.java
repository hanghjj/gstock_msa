package com.gstock.stock.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gstock.gutils.exception.CustomException;
import com.gstock.gutils.util.StringUtils;
import com.gstock.stock.client.KisClient;
import com.gstock.stock.dao.StockDAO;
import com.gstock.stock.dto.StockDto;
import com.gstock.stock.dto.StockPriceDto;
import com.gstock.stock.entity.Stock;
import com.gstock.stock.entity.StockPrice;
import com.gstock.stock.objects.StockObjectMappingService;
import com.gstock.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.gstock.stock.StockConstants.*;

@Service

@RequiredArgsConstructor
@Slf4j
public class StockServiceImpl implements StockService {

    private final StockDAO stockDAO;
    private final KisClient kisClient;
    private final StockObjectMappingService objectMappingService;

    @Override
    public Stock getStock(String srtnCd) {
        return stockDAO.selectStock(srtnCd);
    }

    @Override
    public void saveStock(StockDto stockDto) {
        stockDAO.insertStock(new Stock(stockDto));
    }

    @Override
    public void saveStock(StockPriceDto StockPriceDto) {
        stockDAO.insertStockPrice(new StockPrice(StockPriceDto));
    }

    @Override
    public void updateStock(String srtnCd) throws Exception {
        stockDAO.updateStock(srtnCd);
    }

    @Override
    public void deleteStock(String srtnCd) throws Exception {
        stockDAO.deleteStock(srtnCd);
    }

    @Override
    public StockDto getStockInfoFromKis(String srtnCd) throws JsonProcessingException, CustomException {
        String marketCode = null;
        String stockInfoJson = kisClient.getStockInfo(srtnCd);
        JSONObject output1 = new JSONObject(stockInfoJson).getJSONObject("output");
        //convert Json to StockDto
        StockDto stockDto = objectMappingService.ConvertCTPF1604RToStockDto(output1.toString());
        log.info(stockDto.getItmNm() + "(" + srtnCd + ")" + " 주식 정보 (한국투자증권 조회) 결과 JSON : " + output1.toString());
        if (Optional.ofNullable(stockDto.getItmNm()).orElse("").length() < 1) {
            throw new CustomException("stock.search.fail");
        }
        if (StringUtils.equals(marketCode, AMEX)) { //AMEX 거래소 주식일경우
            stockDto.setExcd(AME);
        } else if (StringUtils.equals(stockDto.getDomeForeSeCd(), FORE)) {
            stockDto.setExcd(NAS);
        } else stockDto.setExcd(KOR);
        return stockDto;
    }

    @Override
    public StockPriceDto getStockPriceFromKis(StockDto stockDto) throws JsonProcessingException {
        String srtnCd = stockDto.getSrtnCd();
        String excd = stockDto.getExcd();
        boolean isDomestic = StringUtils.isDigit(srtnCd);
        String stockPriceJson = kisClient.getStockPrice(srtnCd);
        JSONObject output2 = null;
        try {
            output2 = new JSONObject(stockPriceJson).getJSONObject("output");
        } catch (JSONException e) {
            throw new CustomException("stock.search.fail");
        }
        //convert Json to StockPriceDto
        StockPriceDto stockPriceDto = isDomestic ?
                objectMappingService.ConvertFHKST01010100ToStockPriceDto(output2.toString(), srtnCd) :
                objectMappingService.ConvertHHDFS00000300ToStockPriceDto(output2.toString(), srtnCd);
        if (stockPriceDto.getStkPrpr() == 0) {
            throw new CustomException("stock.unavailable.error", List.of(stockPriceDto.getSrtnCd()));
        }
        log.info(stockDto.getItmNm() + "(" + srtnCd + ")" + " 주식 가격 (한국투자증권 조회) 결과 JSON : " + output2.toString());
        log.info(stockDto.getItmNm() + "(" + srtnCd + ")" + " 주식 가격 (한국투자증권 조회) : " + stockPriceDto.getStkPrpr());
        return stockPriceDto;
    }
}
