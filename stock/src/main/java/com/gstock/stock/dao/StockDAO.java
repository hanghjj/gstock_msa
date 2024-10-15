package com.gstock.stock.dao;

import com.gstock.stock.entity.Stock;
import com.gstock.stock.entity.StockPrice;

public interface StockDAO {

    void insertStock(Stock Stock);
    void insertStockPrice(StockPrice stockPrice);
    Stock selectStock(String srtnCd);
    void updateStock(String srtnCd) throws Exception;
    void deleteStock(String srtnCd) throws Exception;
}
