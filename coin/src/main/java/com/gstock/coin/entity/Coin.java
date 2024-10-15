package  com.gstock.coin.entity;

import com.gstock.coin.dto.CoinDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "GS_COIN")
@NoArgsConstructor
public class Coin extends BaseEntity {
    @Id
    private String ticker;
    @Column(name = "ITM_NM")
    private String itmNm;

    public Coin(CoinDto dto){
        BeanUtils.copyProperties(dto,this);
    }
}
