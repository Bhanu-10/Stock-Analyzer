package com.bhanu.StockAnalyzer.consumer;

import com.bhanu.StockAnalyzer.dto.StockPrice;
import com.bhanu.StockAnalyzer.service.IRedisPriceCache;
import com.bhanu.StockAnalyzer.service.IStockService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StockPriceConsumer {
    private final IStockService iStockService;
    private final IRedisPriceCache iRedisPriceCache;
    private final SimpMessagingTemplate template;


    @KafkaListener(topics = "stock.ticks", groupId = "stock-analyzer-new-group")
    public void onMessage(StockPrice stockPrice) {
        iStockService.save(stockPrice);
        iRedisPriceCache.pushTick(stockPrice);
// broadcast to websocket topic
        template.convertAndSend("/topic/ticks/" + stockPrice.getSymbol(), stockPrice);
    }

}
