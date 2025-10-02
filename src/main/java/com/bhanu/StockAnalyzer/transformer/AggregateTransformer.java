package com.bhanu.StockAnalyzer.transformer;

import com.bhanu.StockAnalyzer.dto.StockAggregate;
import com.bhanu.StockAnalyzer.model.AggregateEntity;

public class AggregateTransformer {

    public static AggregateEntity dtoToEntity(StockAggregate stockAggregate) {

        return AggregateEntity.builder()
                .symbol(stockAggregate.getSymbol())
                .avgPrice(stockAggregate.getAvgPrice())
                .totalVolume(stockAggregate.getTotalVolume())
                .tickCount(stockAggregate.getTickCount())
                .windowStart(stockAggregate.getWindowStart())
                .windowEnd(stockAggregate.getWindowEnd())
                .build();


    }

    public static StockAggregate entityToDTO(AggregateEntity aggregateEntity) {

        return StockAggregate.builder()
                .symbol(aggregateEntity.getSymbol())
                .avgPrice(aggregateEntity.getAvgPrice())
                .totalVolume(aggregateEntity.getTotalVolume())
                .tickCount(aggregateEntity.getTickCount())
                .windowStart(aggregateEntity.getWindowStart())
                .windowEnd(aggregateEntity.getWindowEnd())
                .build();


    }
}
