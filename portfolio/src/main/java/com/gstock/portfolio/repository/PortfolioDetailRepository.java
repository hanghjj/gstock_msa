package com.gstock.portfolio.repository;

import com.gstock.portfolio.entity.PortfolioDetail;
import com.gstock.portfolio.entity.PortfolioDetailId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PortfolioDetailRepository extends JpaRepository<PortfolioDetail, PortfolioDetailId> {
    List<PortfolioDetail> findByIdPortfolioId(String portfolioId);
}
