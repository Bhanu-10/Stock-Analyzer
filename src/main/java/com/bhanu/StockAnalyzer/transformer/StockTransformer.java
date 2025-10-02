package com.bhanu.StockAnalyzer.transformer;

import com.bhanu.StockAnalyzer.dto.StockPrice;
import com.bhanu.StockAnalyzer.model.StockEntity;

public class StockTransformer {

    public static StockEntity dtoToEntity(StockPrice stockPrice) {

        return StockEntity.builder()
                .symbol(stockPrice.getSymbol())
                .price(stockPrice.getPrice())
                .volume(stockPrice.getVolume())
                .timestamp(stockPrice.getTimestamp())
                .build();


    }

    public static StockPrice entityToDto(StockEntity stockEntity) {

        return StockPrice.builder()
                .symbol(stockEntity.getSymbol())
                .price(stockEntity.getPrice())
                .volume(stockEntity.getVolume())
                .timestamp(stockEntity.getTimestamp())
                .build();

    }
}
