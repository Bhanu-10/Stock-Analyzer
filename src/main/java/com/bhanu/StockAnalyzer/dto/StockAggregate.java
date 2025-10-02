package com.bhanu.StockAnalyzer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockAggregate {

    private String symbol;
    private double avgPrice;
    private long totalVolume;
    private long tickCount;
    private Timestamp windowStart;
    private Timestamp windowEnd;

    // getters & setters


}
