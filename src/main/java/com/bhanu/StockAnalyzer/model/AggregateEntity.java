package com.bhanu.StockAnalyzer.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;


@Entity
@Table(name = "aggregate")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class AggregateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;
    private double avgPrice;
    private long totalVolume;
    private long tickCount;
    private Timestamp windowStart;
    private Timestamp windowEnd;

}
