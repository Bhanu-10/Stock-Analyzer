package com.bhanu.StockAnalyzer.service;

import com.bhanu.StockAnalyzer.dto.StockPrice;

import java.util.List;

public interface IRedisPriceCache {

    void pushTick(StockPrice stockPrice);

    List<StockPrice> latestTicks(String symbol, int limit);
}
