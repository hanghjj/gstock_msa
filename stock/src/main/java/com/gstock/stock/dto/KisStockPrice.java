package com.gstock.stock.dto;
import com.gstock.gutils.util.DateTimeUtils;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class KisStockPrice {
    private String stckShrnIscd; //종목코드
    private String rprsMrktKorName; // 대표시장 한글명
    private Double stckPrpr; //주식 현재가
    private Double prdyVrss; //전일대비
    private String prdyVrssSign; //전일대비 부호
    private Double prdyCtrt; //전일대비율
    private Double stckOprc; //주식 시가
    private Double stckHgpr; //주식 최고가
    private Double stckLwpr; //주식 최저가
    private Double stckMxpr; //주식 상한가
    private Double stckLlam; //주식 하한가
    private Double per;
    private Double pbr;
    private Double eps;
    private Double bps;
    private Double hgprW52;
    private Double lwprW52;

    public StockPriceDto convert(){
        StockPriceDto stockPriceDto = new StockPriceDto();
        BeanUtils.copyProperties(this,stockPriceDto);

        stockPriceDto.setBseDt(DateTimeUtils.getDateFormat("yyyyMMdd"));
        stockPriceDto.setSrtnCd(this.stckShrnIscd);
        stockPriceDto.setStkPrpr(this.stckPrpr);
        stockPriceDto.setPrdyVrss(this.prdyVrss);
        stockPriceDto.setPrdyVrssSign(this.prdyVrssSign);
        stockPriceDto.setHgpr(this.stckHgpr);
        stockPriceDto.setLwpr(this.stckLwpr);
        stockPriceDto.setMxpr(this.stckMxpr);
        stockPriceDto.setLlam(this.stckLlam);
        return stockPriceDto;
    }
}
