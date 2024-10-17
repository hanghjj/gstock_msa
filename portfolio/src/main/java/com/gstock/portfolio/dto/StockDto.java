package com.gstock.portfolio.dto;

import com.gstock.portfolio.entity.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class StockDto extends BaseEntity {
    private String srtnCd;
    private String itmNm;
    private String domeForeSeCd;
    private String excd;
    private String bseDt;
    private Double stkPrpr;
    private Double prdyVrss;
    private String prdyVrssSign;
    private Double hgpr;
    private Double lwpr;
    private Double mxpr;
    private Double llam;
    private Double sdpr;
    private Double per;
    private Double pbr;
    private Double hgprW52;
    private Double lwprW52;
}
