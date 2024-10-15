package com.gstock.stock.objects;
import com.gstock.stock.dto.StockDto;
import lombok.Data;

@Data
public class CTPF1002R {
    private String pdno;
    private String prdt_type_cd;
    private String mket_id_cd;
    private String scty_grp_id_cd;
    private String excg_dvsn_cd;
    private String setl_mmdd;
    private String lstg_stqt;
    private String lstg_cptl_amt;
    private String cpta;
    private String papr;
    private String issu_pric;
    private String kospi_item_yn;
    private String scts_mket_lstg_dt;
    private String scts_mket_lstg_abol_dt;
    private String kosdaq_mket_lstg_dt;
    private String kosdaq_mket_lstg_abol_dt;
    private String frbd_mket_lstg_dt;
    private String frbd_mket_lstg_abol_dt;
    private String reits_kind_cd;
    private String etf_dvsn_cd;
    private String oilf_fund_yn;
    private String idx_bztp_lcls_cd;
    private String idx_bztp_mcls_cd;
    private String idx_bztp_scls_cd;
    private String stck_kind_cd;
    private String mfnd_opng_dt;
    private String mfnd_end_dt;
    private String dpsi_erlm_cncl_dt;
    private String etf_cu_qty;
    private String prdt_name;
    private String prdt_name120;
    private String prdt_abrv_name;
    private String std_pdno;
    private String prdt_eng_name;
    private String prdt_eng_name120;
    private String prdt_eng_abrv_name;
    private String dpsi_aptm_erlm_yn;
    private String etf_txtn_type_cd;
    private String etf_type_cd;
    private String lstg_abol_dt;
    private String nwst_odst_dvsn_cd;
    private String sbst_pric;
    private String thco_sbst_pric;
    private String thco_sbst_pric_chng_dt;
    private String tr_stop_yn;
    private String dmn_item_yn;
    private String thdt_clpr;
    private String bfdy_clpr;
    private String clpr_chng_dt;
    private String std_idst_clsf_cd;
    private String std_idst_clsf_cd_name;
    private String idx_bztp_lcls_cd_name;
    private String idx_bztp_mcls_cd_name;
    private String idx_bztp_scls_cd_name;
    private String ocr_no;
    private String crfd_item_yn;
    private String elec_scty_yn;

    public StockDto convert(){
        return StockDto
                .builder()
                .itmNm(this.prdt_abrv_name)
                .srtnCd(this.pdno.replaceAll("00000A",""))
                .domeForeSeCd(this.pdno.matches(".*[0-9].*")?"D":"F")
                .build();
    }
}
