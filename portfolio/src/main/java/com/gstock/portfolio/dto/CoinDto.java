package com.gstock.portfolio.dto;

import com.gstock.portfolio.entity.BaseEntity;
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
}
