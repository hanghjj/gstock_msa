package com.gstock.stock.entity;

import com.gstock.stock.dto.StockPriceDto;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "GS_STOCK_PRICE")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class StockPrice {
    @EmbeddedId
    private StockPriceId stockPriceId;
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
    @Column(name = "HGPR_W52")
    private Double hgprW52;
    @Column(name = "LWPR_W52")
    private Double lwprW52;

    public StockPrice(StockPriceDto stockPriceDto) {
        BeanUtils.copyProperties(stockPriceDto, this);
        this.stockPriceId = new StockPriceId(stockPriceDto.getBseDt(), stockPriceDto.getSrtnCd());
    }
}
