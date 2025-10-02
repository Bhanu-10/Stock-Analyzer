package com.bhanu.StockAnalyzer.service.impl;

import com.bhanu.StockAnalyzer.dto.StockPrice;
import com.bhanu.StockAnalyzer.service.IRedisPriceCache;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RedisPriceCache implements IRedisPriceCache {
    private final RedisTemplate<String, StockPrice> redis;
    private final int MAX_CACHE = 200; // keep last 200 ticks


    @Override
    public void pushTick(StockPrice stockPrice) {
        ListOperations<String, StockPrice> ops = redis.opsForList();
        String key = "ticks:" + stockPrice.getSymbol();
        ops.leftPush(key, stockPrice);
        ops.trim(key, 0, MAX_CACHE - 1);
    }


    @Override
    public List<StockPrice> latestTicks(String symbol, int limit) {
        return redis.opsForList().range("ticks:" + symbol, 0, limit - 1);
    }
}
