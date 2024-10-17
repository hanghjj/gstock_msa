package com.gstock.portfolio.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioDetailId extends BaseEntity {
    @Schema(name = "포트폴리오ID")
    @Column(name = "PORTFOLIO_ID")
    private String portfolioId;

    @Schema(name = "티커")
    @Column(name = "TICKER")
    private String ticker;
}
