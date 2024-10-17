package com.gstock.portfolio.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gstock.gutils.exception.CustomException;
import com.gstock.gutils.util.StringUtils;
import com.gstock.portfolio.clients.CoinClient;
import com.gstock.portfolio.clients.StockClient;
import com.gstock.portfolio.dto.CoinDto;
import com.gstock.portfolio.dto.PortfolioDto;
import com.gstock.portfolio.dto.StockDto;
import com.gstock.portfolio.entity.Portfolio;
import com.gstock.portfolio.entity.PortfolioDetail;
import com.gstock.portfolio.entity.PortfolioDetailId;
import com.gstock.portfolio.entity.PortfolioId;
import com.gstock.portfolio.repository.PortfolioDetailRepository;
import com.gstock.portfolio.repository.PortfolioRepository;
import com.gstock.portfolio.service.PortfolioService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.gstock.portfolio.PortfolioConstants.*;


@Service
@Slf4j
@AllArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {
    private final StockClient stockClient;
    private final CoinClient coinClient;
    private final PortfolioRepository portfolioRepository;
    private final PortfolioDetailRepository portfolioDetailRepository;

    @Override
    public List<PortfolioDto> getPortfolioList(String userId) {
        // get portfolioId per user
        List<Portfolio> entityList = portfolioRepository.findByIdUserId(userId);
        if (entityList.size() == 0) return Collections.emptyList();
        return entityList.stream()
                .map(this::convertPortfolioToDto)
                .toList();
    }

    @Override
    public List<PortfolioDto> getPortfolioDetailList(String portfolioId) {
        // getPortFolioDetailList
        List<PortfolioDetail> entityList = portfolioDetailRepository.findByIdPortfolioId(portfolioId);
        if (entityList.size() == 0) return Collections.emptyList();
        List<PortfolioDto> resultList = entityList.stream().map(this::convertPortfolioToDto).toList();
        resultList.forEach(this::getLatestPrice);
        // return updated detailList
        return resultList;
    }

    @Override
    public PortfolioDto getLatestPrice(PortfolioDto dto){
        String asstSeCd = dto.getAsstSeCd();
        String ticker = dto.getTicker();
        Double qty = dto.getQty();
        double pcsPce = new BigDecimal(dto.getAvgPcsPce() * qty).doubleValue(); //매수평균가 * 수량 = 매수금액
        switch (asstSeCd) {
            case ASSET_STOCK:
                StockDto stockDto = stockClient.getStockInfo(ticker);
                double mktPce = new BigDecimal(stockDto.getStkPrpr() * qty).doubleValue();//주식현재가 * 수량 = 평가금액
                dto.setMktPce(mktPce);
                dto.setMktPnl(mktPce - pcsPce);
                break;
            case ASSET_COIN:
                CoinDto coinDto = coinClient.getCoinInfo(ticker);
                mktPce = new BigDecimal(coinDto.getPrpr() * qty).doubleValue();//코인현재가 * 수량 = 평가금액
                dto.setMktPce(mktPce);
                dto.setMktPnl(mktPce - pcsPce);
                break;
            case ASSET_USD:
            case ASSET_KRW:
                dto.setMktPce(pcsPce);
                dto.setMktPnl(0d);
                break;
        }
        return dto;
    }

    @Override
    public void insertPortfolio(PortfolioDto portfolioDto) {
        PortfolioId portfolioId = new PortfolioId(portfolioDto.getUserId(), portfolioDto.getPortfolioId());
        Portfolio portfolio = new Portfolio();
        portfolio.setId(portfolioId);
        portfolioRepository.save(portfolio);
        if (!StringUtils.isEmpty(portfolioDto.getTicker())) this.insertPortfolioDetails(portfolioDto);
    }

    @Override
    public void insertPortfolioDetails(PortfolioDto dto) {
        PortfolioDetailId portfolioDetailId = new PortfolioDetailId(dto.getPortfolioId(), dto.getTicker());
        PortfolioDetail detail = new PortfolioDetail();
        detail.setId(portfolioDetailId);
        BeanUtils.copyProperties(dto, detail);
        portfolioDetailRepository.save(detail);
    }

    @Override
    public void deletePortfolio(String userId, String portfolioId) {
        portfolioRepository.deleteById(new PortfolioId(userId, portfolioId));
    }

    @Override
    public void deletePortfolioDetails(String userId, String portfolioId, String ticker) {
        //session 로그인 이후 JWT 토큰에 id와 parameter의 id가 다르면 return
        portfolioDetailRepository.deleteById(new PortfolioDetailId(portfolioId, ticker));
    }

    @Override
    public PortfolioDto convertPortfolioToDto(Portfolio portfolio) {
        PortfolioDto portfolioDto = new PortfolioDto();
        BeanUtils.copyProperties(portfolio, portfolioDto);
        portfolioDto.setUserId(portfolio.getId().getUserId());
        portfolioDto.setPortfolioId(portfolio.getId().getPortfolioId());
        return portfolioDto;
    }

    @Override
    public PortfolioDto convertPortfolioToDto(PortfolioDetail portfolioDetail) {
        PortfolioDto portfolioDto = new PortfolioDto();
        BeanUtils.copyProperties(portfolioDetail, portfolioDto);
        portfolioDto.setPortfolioId(portfolioDetail.getId().getPortfolioId());
        portfolioDto.setTicker(portfolioDetail.getId().getTicker());
        return portfolioDto;
    }

    @Override
    public PortfolioDto scaleTradingSimulation(PortfolioDto portfolioDto, Double addQty, @Nullable Double pcsPce){
        String ticker = portfolioDto.getTicker();

        pcsPce = Optional.ofNullable(pcsPce)
                .orElse(stockClient.getStockInfo(ticker).getStkPrpr());
        //매수가가 비어있을 경우 현재 가격으로 설정

        double qty = portfolioDto.getQty();
        double avgPcsPce = portfolioDto.getAvgPcsPce();
        double resultQty = new BigDecimal(qty).add(new BigDecimal(addQty)).doubleValue();
        double resultPce = (qty * avgPcsPce + addQty * pcsPce) / resultQty;
        if (StringUtils.isDigit(portfolioDto.getTicker())) resultPce = Math.round(resultPce);
        portfolioDto.setQty(resultQty);
        portfolioDto.setAvgPcsPce(resultPce);
        return portfolioDto;
    }
}
