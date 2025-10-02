package com.bhanu.StockAnalyzer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockPrice {
    private String symbol;
    private double price;
    private long volume;
    private Instant timestamp;
}
