package com.gstock.coin.entity;
import com.gstock.coin.dto.CoinDto;
import com.gstock.gutils.util.DateTimeUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "GS_COIN_PRICE")
@NoArgsConstructor
public class CoinPrice extends BaseEntity {
    @EmbeddedId
    private CoinPriceId id;
    @Schema(description = "현재가격")
    private Double prpr;
    @Schema(description = "최고가")
    private Double hgpr;
    @Schema(description = "최저가")
    private Double lwpr;

    public CoinPrice(CoinDto dto) {
        this.id = new CoinPriceId(DateTimeUtils.getDateFormat("yyyyMMdd"), dto.getTicker());
        BeanUtils.copyProperties(dto, this);
    }
}
