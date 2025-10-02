package com.bhanu.StockAnalyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableKafkaStreams
//@EnableScheduling
public class RealtimeStockAnalyzerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealtimeStockAnalyzerApplication.class, args);
    }

}
