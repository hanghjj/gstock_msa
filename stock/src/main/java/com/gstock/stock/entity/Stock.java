package com.gstock.stock.entity;
import com.gstock.stock.dto.StockDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "GS_STOCK")
@Data
@NoArgsConstructor
public class Stock extends BaseEntity {
    @Id
    private String srtnCd;
    private String itmNm;
    private String domeForeSeCd;

    public Stock(String srtnCd, String itmNm, String domeForeSeCd) {
        this.setSrtnCd(srtnCd);
        this.setItmNm(itmNm);
        this.setDomeForeSeCd(domeForeSeCd);
    }

    public Stock(StockDto stockDto) {
        BeanUtils.copyProperties(stockDto, this);
    }
}
