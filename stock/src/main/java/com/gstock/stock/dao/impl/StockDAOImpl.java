package com.gstock.stock.dao.impl;
import com.gstock.stock.dao.StockDAO;
import com.gstock.stock.entity.Stock;
import com.gstock.stock.entity.StockPrice;
import com.gstock.stock.repository.StockPriceRepository;
import com.gstock.stock.repository.StockRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class StockDAOImpl implements StockDAO {

    private final StockRepository stockRepository;
    private final StockPriceRepository stockPriceRepository;
    @Override
    public void insertStock(Stock stock){
        log.info("Insert Stock : " + stock.toString());
        stockRepository.save(stock);
    }
    @Override
    @Transactional
    public void insertStockPrice(StockPrice stockPrice){
        log.info("Insert Stock price: " + stockPrice.toString());
        stockPriceRepository.save(stockPrice);
    }
    @Override
    public Stock selectStock(String srtnCd){
        return stockRepository.findById(srtnCd).orElse(new Stock());
    }
    @Override
    public void updateStock(String srtnCd) throws Exception{
        Optional<Stock> target = stockRepository.findById(srtnCd);

        if(target.isPresent()){
            Stock stock = target.get();
            stockRepository.save(stock);
        }else {
            log.error("입력하신 정보에 해당하는 데이터가 존재하지 않습니다.\n");
        }
    }
    @Override
    public void deleteStock(String srtnCd) throws Exception{
        Optional<Stock> target = stockRepository.findById(srtnCd);
        if(target.isPresent()){
            Stock stock = target.get();
            stockRepository.delete(stock);
        }else {
            log.error("입력하신 정보에 해당하는 데이터가 존재하지 않습니다.\n");
        }
    }

}
