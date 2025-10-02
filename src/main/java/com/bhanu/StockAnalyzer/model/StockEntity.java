package com.bhanu.StockAnalyzer.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;


@Entity
@Table(name = "stock_price_history")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class StockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String symbol;
    private double price;
    private long volume;
    private Instant timestamp;


}
