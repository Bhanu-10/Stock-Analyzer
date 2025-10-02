package com.bhanu.StockAnalyzer.repository;

import com.bhanu.StockAnalyzer.model.AggregateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStockAggregateRepository extends JpaRepository<AggregateEntity, Long> {

    List<AggregateEntity> findAllBySymbol(String symbol);
}

