package com.gstock.coin.dto;

import com.gstock.coin.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CoinDto extends BaseEntity {
    private String ticker;
    private String itmNm;
    private Double prpr;
    private Double hgpr;
    private Double lwpr;

    public CoinDto(CoinOneDto coinOneDto){
        this.ticker = coinOneDto.getTarget_currency();
        this.prpr = Double.parseDouble(coinOneDto.getLast());
        this.hgpr = Double.parseDouble(coinOneDto.getHigh());
        this.lwpr = Double.parseDouble(coinOneDto.getLow());
    }
}
