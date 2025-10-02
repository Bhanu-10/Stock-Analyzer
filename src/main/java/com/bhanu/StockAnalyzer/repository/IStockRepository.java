package com.bhanu.StockAnalyzer.repository;

import com.bhanu.StockAnalyzer.model.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStockRepository extends JpaRepository<StockEntity, Long> {

    List<StockEntity> findTop100BySymbolOrderByTimestampDesc(String symbol);
}
