package com.gstock.coin.dto;

import lombok.Data;

import java.util.List;

@Data
public class CoinOneDto {
    private String target_currency;
    private String high;
    private String low;
    private String first;
    private String last;
}
