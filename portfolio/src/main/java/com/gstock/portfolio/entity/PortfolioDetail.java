package com.gstock.portfolio.entity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "GS_PORTFOLIO_DTIL")
@Data
@NoArgsConstructor
public class PortfolioDetail extends BaseEntity {
    @EmbeddedId
    private PortfolioDetailId id;

    @Schema(name = "자산구분코드", allowableValues = {"C", "S", "U", "K"})
    @Column(name = "ASST_SE_CD")
    private String asstSeCd;

    @Schema(name = "보유수량")
    @Column(name = "QTY")
    private Double qty;

    @Schema(name = "매수평균가")
    @Column(name = "AVG_PCS_PCE")
    private Double avgPcsPce;
}
