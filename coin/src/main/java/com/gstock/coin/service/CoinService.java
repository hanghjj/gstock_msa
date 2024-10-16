package com.gstock.coin.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gstock.coin.dto.CoinDto;

public interface CoinService{
    CoinDto getCoin(String ticker) throws JsonProcessingException;
    void saveCoin(CoinDto coin);

    CoinDto getCoinPrice(String ticker);
    CoinDto getCoinInfo(String ticker);
}
