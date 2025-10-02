package com.bhanu.StockAnalyzer.controller;

import com.bhanu.StockAnalyzer.dto.StockPrice;
import com.bhanu.StockAnalyzer.producer.StockPriceProducer;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

//@Component
@RequiredArgsConstructor
public class ManualStockProducer {

    private final StockPriceProducer producer;

    @PostConstruct
    public void sendManualTicks() {
        StockPrice tick1 = new StockPrice("AAPL", 200, 600, Instant.now());
        StockPrice tick2 = new StockPrice("AAPL", 210, 500, Instant.now());
        StockPrice tick3 = new StockPrice("AAPL", 205, 400, Instant.now());

        producer.sendRandomTick(tick1);
        producer.sendRandomTick(tick2);
        producer.sendRandomTick(tick3);

        System.out.println("✅ Sent 3 manual ticks to stock.ticks");
    }
}

