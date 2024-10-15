package com.gstock.stock.repository;

import com.gstock.stock.entity.StockPrice;
import com.gstock.stock.entity.StockPriceId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockPriceRepository extends JpaRepository<StockPrice, StockPriceId> {
}
