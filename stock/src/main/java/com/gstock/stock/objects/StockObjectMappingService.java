package com.gstock.stock.objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gstock.stock.dto.KisStockPrice;
import com.gstock.stock.dto.StockDto;
import com.gstock.stock.dto.StockPriceDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class StockObjectMappingService {

    private final CustomObjectMapper mapper;

    public StockPriceDto ConvertFHKST01010100ToStockPriceDto(String jsonString, String srtnCd) throws JsonProcessingException {
        //convert json to apiObj
        FHKST01010100 fhkst01010100 = mapper.readValue(jsonString, FHKST01010100.class);
        // apiObj to KisStockPrice
        KisStockPrice convert = fhkst01010100.convert(srtnCd);
        //convert KisStockPrice to StockPrice
        return convert.convert();
    }
    public StockPriceDto ConvertHHDFS00000300ToStockPriceDto(String jsonString, String srtnCd) throws JsonProcessingException {
        //convert json to apiObj
        HHDFS00000300 hhdfs00000300 = mapper.readValue(jsonString, HHDFS00000300.class);
        // apiObj to KisStockPrice
        KisStockPrice convert = hhdfs00000300.convert();
        //convert KisStockPrice to StockPrice
        return convert.convert();
    }
    public KisStockPrice ConvertFHKST01010100ToKisStockPrice(String jsonString, String srtnCd) throws JsonProcessingException {
        //convert json to apiObj
        FHKST01010100 fhkst01010100 = mapper.readValue(jsonString, FHKST01010100.class);
        // apiObj to KisStockPrice
        return fhkst01010100.convert(srtnCd);
    }
    public StockDto ConvertCTPF1002RToStockDto(String jsonString) throws JsonProcessingException {
        //convert json to apiObj
        CTPF1002R ctpf1002R = mapper.readValue(jsonString, CTPF1002R.class);
        //convert apiObj to StockDto
        return ctpf1002R.convert();
    }

    public StockDto ConvertCTPF1604RToStockDto(String jsonString) throws JsonProcessingException {
        //convert json to apiObj
        CTPF1604R ctpf1604R = mapper.readValue(jsonString, CTPF1604R.class);
        //convert apiObj to StockDto
        return ctpf1604R.convert();
    }
}
