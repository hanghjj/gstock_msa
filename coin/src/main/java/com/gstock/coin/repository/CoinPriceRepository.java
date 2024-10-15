package com.gstock.coin.repository;
import com.gstock.coin.entity.CoinPrice;
import com.gstock.coin.entity.CoinPriceId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinPriceRepository extends JpaRepository<CoinPrice, CoinPriceId> {
}
