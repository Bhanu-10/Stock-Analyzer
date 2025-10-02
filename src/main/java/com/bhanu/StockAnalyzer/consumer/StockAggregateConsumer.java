package com.bhanu.StockAnalyzer.consumer;

import com.bhanu.StockAnalyzer.dto.StockAggregate;
import com.bhanu.StockAnalyzer.repository.IStockAggregateRepository;
import com.bhanu.StockAnalyzer.transformer.AggregateTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockAggregateConsumer {

    private final IStockAggregateRepository repository; // JPA repository

    @KafkaListener(topics = "stock.aggregates", groupId = "stock-analyzer-new-group")
    public void consume(StockAggregate aggregate) {
        repository.save(AggregateTransformer.dtoToEntity(aggregate));
    }
}
