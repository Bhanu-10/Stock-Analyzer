package com.bhanu.StockAnalyzer.service.impl;

import com.bhanu.StockAnalyzer.dto.StockPrice;
import com.bhanu.StockAnalyzer.model.StockEntity;
import com.bhanu.StockAnalyzer.repository.IStockRepository;
import com.bhanu.StockAnalyzer.service.IStockService;
import com.bhanu.StockAnalyzer.transformer.StockTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService implements IStockService {
    private final IStockRepository iStockRepository;

    @Override
    public void save(StockPrice stockPrice) {
        StockEntity stockEntity = StockTransformer.dtoToEntity(stockPrice);
        iStockRepository.save(stockEntity);
    }

    @Override
    public List<StockPrice> findTop100BySymbolOrderByTimestampDesc(String symbol) {
        List<StockEntity> stockEntityList = iStockRepository.findTop100BySymbolOrderByTimestampDesc(symbol);
        return stockEntityList.stream()
                .map(ele -> StockTransformer.entityToDto(ele))
                .toList();


    }
}
