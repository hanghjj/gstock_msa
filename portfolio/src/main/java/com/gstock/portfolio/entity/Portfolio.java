package com.gstock.portfolio.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "GS_PORTFOLIO")
@Data
@NoArgsConstructor
public class Portfolio extends BaseEntity {

    @EmbeddedId
    private PortfolioId id;

    @Column(name = "UP_YMD")
    private String upYmd;

}
