package com.gstock.stock.dto;
import com.gstock.stock.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class StockPriceDto extends BaseEntity {

    private String bseDt;
    private String srtnCd;
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
