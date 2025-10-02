package com.bhanu.StockAnalyzer.service;

import com.bhanu.StockAnalyzer.dto.StockPrice;

import java.util.List;

public interface IStockService {
    void save(StockPrice stockPrice);

    List<StockPrice> findTop100BySymbolOrderByTimestampDesc(String symbol);
}
