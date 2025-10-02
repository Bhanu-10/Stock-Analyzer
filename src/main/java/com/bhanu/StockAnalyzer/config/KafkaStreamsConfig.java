package com.bhanu.StockAnalyzer.config;

import com.bhanu.StockAnalyzer.dto.StockAggregate;
import com.bhanu.StockAnalyzer.dto.StockPrice;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.support.serializer.JsonSerde;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.Properties;

@Configuration
@EnableKafkaStreams
public class KafkaStreamsConfig {

    public static final String INPUT_TOPIC = "stock.ticks";
    public static final String OUTPUT_TOPIC = "stock.aggregates";

    @Bean(name = "kafkaStreamsProperties")
    public Properties kafkaStreamsProperties() {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "stock-analyzer-streams");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, JsonSerde.class);
        return props;
    }

    @Bean
    public KStream<String, StockPrice> kStream(StreamsBuilder streamsBuilder) {
        JsonSerde<StockPrice> stockPriceSerde = new JsonSerde<>(StockPrice.class);
        JsonSerde<StockAggregate> stockAggregateSerde = new JsonSerde<>(StockAggregate.class);

        KStream<String, StockPrice> stream = streamsBuilder.stream(INPUT_TOPIC,
                Consumed.with(Serdes.String(), stockPriceSerde));

        // Compute 1-minute tumbling window aggregates
        stream.groupByKey(Grouped.with(Serdes.String(), stockPriceSerde))
                .windowedBy(TimeWindows.of(Duration.ofMinutes(1)))
                .aggregate(
                        StockAggregate::new, // initializer
                        (key, value, aggregate) -> {
                            aggregate.setSymbol(value.getSymbol());
                            aggregate.setTotalVolume(aggregate.getTotalVolume() + value.getVolume());
                            aggregate.setTickCount(aggregate.getTickCount() + 1);
                            aggregate.setAvgPrice((aggregate.getAvgPrice() * (aggregate.getTickCount() - 1) + value.getPrice()) / aggregate.getTickCount());
                            return aggregate;
                        },
                        Materialized.with(Serdes.String(), stockAggregateSerde)
                )
                .toStream()
                .map((windowedKey, aggregate) -> {
                    aggregate.setWindowStart(Timestamp.from(windowedKey.window().startTime()));
                    aggregate.setWindowEnd(Timestamp.from(windowedKey.window().endTime()));
                    return KeyValue.pair(windowedKey.key(), aggregate);
                })
                .to(OUTPUT_TOPIC, Produced.with(Serdes.String(), stockAggregateSerde));

        return stream;


    }
}
