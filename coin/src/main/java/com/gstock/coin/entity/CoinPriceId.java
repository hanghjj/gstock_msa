package com.gstock.coin.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoinPriceId extends BaseEntity {
    private String bseDt;
    private String ticker;
}
