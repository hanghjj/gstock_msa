package com.gstock.portfolio.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gstock.portfolio.dto.PortfolioDto;
import com.gstock.portfolio.entity.Portfolio;
import com.gstock.portfolio.entity.PortfolioDetail;
import org.springframework.lang.Nullable;

import java.util.List;

public interface PortfolioService {
    List<PortfolioDto> getPortfolioList(String userId);
    List<PortfolioDto> getPortfolioDetailList(String portfolioId);
    PortfolioDto getLatestPrice(PortfolioDto dto) throws JsonProcessingException;
    void insertPortfolio(PortfolioDto portfolioDto);
    void insertPortfolioDetails(PortfolioDto portfolioDto);
    void deletePortfolio(String userId, String portfolioId);
    void deletePortfolioDetails(String userId, String portfolioId, String ticker);
    PortfolioDto convertPortfolioToDto(Portfolio portfolio);
    PortfolioDto convertPortfolioToDto(PortfolioDetail portfolioDetail);
    PortfolioDto scaleTradingSimulation(PortfolioDto portfolioDto, Double addQty, @Nullable Double pcsPce) throws JsonProcessingException;
}
