package com.gstock.coin.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gstock.coin.client.CoinOneClient;
import com.gstock.coin.dto.CoinDto;
import com.gstock.coin.entity.Coin;
import com.gstock.coin.entity.CoinPrice;
import com.gstock.coin.objects.CoinObjectMappingService;
import com.gstock.coin.repository.CoinPriceRepository;
import com.gstock.coin.repository.CoinRepository;
import com.gstock.coin.service.CoinService;
import com.gstock.gutils.exception.CustomException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.gstock.coin.CoinConstants.COIN_ONE_COIN_INFO;
import static com.gstock.coin.CoinConstants.COIN_ONE_COIN_PRICE;

@Slf4j
@Service
@AllArgsConstructor
public class CoinServiceImpl implements CoinService {
    private final CoinRepository repository;
    private final CoinPriceRepository priceRepository;
    private final CoinObjectMappingService mappingService;
    private final CoinOneClient coinOneClient;

    @Override
    public CoinDto getCoin(String ticker) throws CustomException {
        String priceUrl = COIN_ONE_COIN_PRICE + ticker;
        String infoUrl = COIN_ONE_COIN_INFO + ticker;
        RestTemplate template = new RestTemplate();
        String priceResult = template.getForObject(priceUrl, String.class);
        CoinDto resultDto;
        try {
            String target = new JSONObject(priceResult).getJSONArray("tickers").get(0).toString();
            String infoResult = template.getForObject(infoUrl, String.class);
            JSONObject currencyInfo = new JSONObject(infoResult).getJSONArray("currencies").getJSONObject(0);
            String name = currencyInfo.getString("name");
            String symbol = currencyInfo.getString("symbol");
            resultDto = mappingService.ConvertCoinOneDtoToCoinDto(target);
            resultDto.setItmNm(name);
            resultDto.setTicker(symbol);
            saveCoin(resultDto);
        } catch (JSONException | JsonProcessingException e) {
            throw new CustomException("coin.search.fail");
        }

        log.info(resultDto.getTicker() + " 현재가 조회(CoinOne Public Api) 결과 : " + resultDto.getPrpr());
        return resultDto;
    }

    @Override
    public void saveCoin(CoinDto coin) {
        repository.save(new Coin(coin));
        priceRepository.save(new CoinPrice(coin));
    }

    @Override
    public CoinDto getCoinPrice(String ticker) {
        String jsonString = coinOneClient.getCoinPrice(ticker);
        CoinDto coinDto;
        try{
            String target = new JSONObject(jsonString).getJSONArray("tickers").get(0).toString();
            coinDto = mappingService.ConvertCoinOneDtoToCoinDto(target);
        }catch (JSONException | JsonProcessingException e) {
            throw new CustomException("coin.search.fail");
        }
        coinDto.setTicker(ticker);
        return coinDto;
    }

    @Override
    public CoinDto getCoinInfo(String ticker) {
        String jsonString = coinOneClient.getCoinInfo(ticker);
        CoinDto coinDto = new CoinDto();
        try{
            String target = new JSONObject(jsonString).getJSONArray("currencies").get(0).toString();
            JSONObject currencyInfo = new JSONObject(target);
            String name = currencyInfo.getString("name");
            String symbol = currencyInfo.getString("symbol");
            coinDto.setItmNm(name);
            coinDto.setTicker(symbol);
        }catch (JSONException  e) {
            throw new CustomException("coin.search.fail");
        }
        return coinDto;
    }
}
