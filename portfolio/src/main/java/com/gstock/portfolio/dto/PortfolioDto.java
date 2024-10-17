package com.gstock.portfolio.dto;

import com.gstock.gutils.util.DateTimeUtils;
import com.gstock.portfolio.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PortfolioDto extends BaseEntity {
    private String userId;
    private String portfolioId;
    private String upYmd;
    @Schema(name = "자산 구분 코드", allowableValues = {"C","S","U","K"})
    private String asstSeCd;
    @Schema(name = "주식코드 OR 티커")
    private String ticker;
    @Schema(name = "수량")
    private Double qty;
    @Schema(name = "매수평균가")
    private Double avgPcsPce;
    @Schema(name = "평가금액")
    private Double mktPce;
    @Schema(name = "평가손익")
    private Double mktPnl;

    public PortfolioDto(String userId, String portfolioId, String asstSeCd, String ticker, Double qty, Double avgPcsPce){
        this.userId = userId;
        this.upYmd = DateTimeUtils.getDateFormat("yyyyMMdd");
        this.portfolioId = portfolioId;
        this.asstSeCd = asstSeCd;
        this.ticker = ticker;
        this.qty = qty;
        this.avgPcsPce = avgPcsPce;
    }

    public PortfolioDto(String userId, String portfolioId){
        this.userId = userId;
        this.portfolioId = portfolioId;
    }

    public PortfolioDto(String userId, String portfolioId, String ticker){
        this.userId = userId;
        this.portfolioId = portfolioId;
        this.ticker = ticker;
    }

    public PortfolioDto(String ticker, Double qty, Double avgPcsPce){
        this.ticker = ticker;
        this.qty = qty;
        this.avgPcsPce = avgPcsPce;
    }
}
