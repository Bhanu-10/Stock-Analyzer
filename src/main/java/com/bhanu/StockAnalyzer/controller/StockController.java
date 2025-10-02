package com.bhanu.StockAnalyzer.controller;


import com.bhanu.StockAnalyzer.dto.StockAggregate;
import com.bhanu.StockAnalyzer.dto.StockPrice;
import com.bhanu.StockAnalyzer.model.AggregateEntity;
import com.bhanu.StockAnalyzer.producer.StockPriceProducer;
import com.bhanu.StockAnalyzer.repository.IStockAggregateRepository;
import com.bhanu.StockAnalyzer.service.IRedisPriceCache;
import com.bhanu.StockAnalyzer.service.IStockService;
import com.bhanu.StockAnalyzer.transformer.AggregateTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
public class StockController {
    private final IStockService iStockService;
    private final IRedisPriceCache iRedisPriceCache;
    private final StockPriceProducer stockPriceProducer;
    private final IStockAggregateRepository iStockAggregateRepository;


    /**
     * Publish a stock price to Kafka.
     *
     * @param stockPrice the stock price to publish
     * @return a message indicating that the data has been published
     */

    @PostMapping("/publish")
    public String publish(@RequestBody StockPrice stockPrice) {
        stockPriceProducer.sendRandomTick(stockPrice);
        return "Data has been published";

    }


    /**
     * Get the latest stock prices for a symbol.
     *
     * @param symbol the symbol of the stock
     * @param limit  the number of latest stock prices to retrieve
     * @return a list of the latest stock prices for the symbol
     */
    @GetMapping("/{symbol}/latest")
    public List<StockPrice> latest(@PathVariable String symbol, @RequestParam(defaultValue = "50") int limit) {
        return iRedisPriceCache.latestTicks(symbol, limit);
    }


    /**
     * Get the history of stock prices for a symbol.
     *
     * @param symbol the symbol of the stock
     * @param limit  the number of stock prices to retrieve
     * @return a list of the stock prices for the symbol
     */
    @GetMapping("/{symbol}/history")
    public List<StockPrice> history(@PathVariable String symbol, @RequestParam(defaultValue = "100") int limit) {
        return iStockService.findTop100BySymbolOrderByTimestampDesc(symbol);
    }


    /**
     * Get the aggregates of stock prices for a symbol.
     *
     * @param symbol the symbol of the stock
     * @return a list of the aggregates for the symbol
     */
    @GetMapping("/{symbol}/aggregates")
    public List<StockAggregate> aggregateList(@PathVariable String symbol) {

        List<AggregateEntity> aggregateEntities = iStockAggregateRepository.findAllBySymbol(symbol);

        return aggregateEntities.stream()
                .map(AggregateTransformer::entityToDTO)
                .toList();

    }


}