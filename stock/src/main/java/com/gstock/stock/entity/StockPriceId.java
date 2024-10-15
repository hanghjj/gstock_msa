package com.gstock.stock.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
public class StockPriceId implements Serializable {
    private String bseDt;
    private String srtnCd;

    public StockPriceId(String bseDt, String srtnCd) {
        this.bseDt = bseDt;
        this.srtnCd = srtnCd;
    }
}
