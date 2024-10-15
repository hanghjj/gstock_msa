package com.gstock.stock.objects;
import com.gstock.stock.dto.KisStockPrice;
import com.gstock.stock.utils.StockUtils;
import lombok.Data;

@Data
public class HHDFS00000300 {
    private String rsym;
    private String zdiv;
    private String base;
    private String pvol;
    private String last;
    private String sign;
    private String diff;
    private String rate;
    private String tvol;
    private String tamt;
    private String ordy;
    private String rt_cd;
    private String msg_cd;
    private String msg1;

    public KisStockPrice convert(){
        KisStockPrice a = new KisStockPrice();
        String DMarket = this.getRsym().substring(0,4);
        a.setStckShrnIscd(this.rsym.replaceAll(DMarket,""));
        a.setRprsMrktKorName("미국 " + (DMarket.substring(1).equals("NAS")?"나스닥":"아멕스"));
        a.setStckPrpr(Double.parseDouble(this.last));
        a.setPrdyVrss(Double.parseDouble(this.diff));
        a.setPrdyVrssSign(StockUtils.getVrssSign(this.sign));
        a.setPrdyCtrt(Double.parseDouble(this.rate));
        return a;
    }
}
