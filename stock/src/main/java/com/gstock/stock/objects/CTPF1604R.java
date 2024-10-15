package com.gstock.stock.objects;
import com.gstock.gutils.util.StringUtils;
import com.gstock.stock.dto.StockDto;
import lombok.Data;

@Data
public class CTPF1604R {
    private String rt_cd;
    private String msg_cd;
    private String msg1;
    private String output;
    private String pdno;
    private String prdt_type_cd;
    private String prdt_name;
    private String prdt_name120;
    private String prdt_abrv_name;
    private String prdt_eng_name;
    private String prdt_eng_name120;
    private String prdt_eng_abrv_name;
    private String std_pdno;
    private String shtn_pdno;
    private String prdt_sale_stat_cd;
    private String prdt_risk_grad_cd;
    private String prdt_clsf_cd;
    private String prdt_clsf_name;
    private String sale_strt_dt;
    private String sale_end_dt;
    private String wrap_asst_type_cd;
    private String ivst_prdt_type_cd;
    private String ivst_prdt_type_cd_name;
    private String frst_erlm_dt;

    public StockDto convert(){
        return StockDto
                .builder()
                .itmNm(this.prdt_abrv_name)
                .srtnCd(this.shtn_pdno)
                .domeForeSeCd(StringUtils.isDigit(this.shtn_pdno) ?"D":"F")
                .build();
    }
}
