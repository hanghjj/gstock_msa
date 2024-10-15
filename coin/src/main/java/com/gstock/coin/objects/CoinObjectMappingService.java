package com.gstock.coin.objects;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.gstock.coin.dto.CoinDto;
import com.gstock.coin.dto.CoinOneDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class CoinObjectMappingService {
    private final CustomObjectMapper mapper;

    public CoinDto ConvertCoinOneDtoToCoinDto(String jsonString) throws JsonProcessingException {
        CoinOneDto coinOneDto = mapper.readValue(jsonString, CoinOneDto.class);
        return new CoinDto(coinOneDto);
    }
}
