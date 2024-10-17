package com.gstock.portfolio.repository;


import com.gstock.portfolio.entity.Portfolio;
import com.gstock.portfolio.entity.PortfolioId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PortfolioRepository extends JpaRepository<Portfolio, PortfolioId> {
    List<Portfolio> findByIdUserId(String userId);
}
