package com.bhanu.StockAnalyzer.producer;

import com.bhanu.StockAnalyzer.dto.StockPrice;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockPriceProducer {
    private final KafkaTemplate<String, StockPrice> kafkaTemplate;
    @Value("${kafka.topics.ticks}")
    private String ticksTopic;

    public void sendRandomTick(StockPrice stockPrice) {
        /*Message<StockPrice> message = MessageBuilder.withPayload(stockPrice)
                .setHeader(KafkaHeaders.TOPIC, ticksTopic)
                .setHeader(KafkaHeaders.KEY, stockPrice.getSymbol())
                .build();
        kafkaTemplate.send(message);*/
        System.out.println("Sending tick: " + stockPrice.getSymbol() + " -> " + stockPrice);
        kafkaTemplate.send(ticksTopic, stockPrice.getSymbol(), stockPrice);

    }
}

