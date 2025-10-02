package com.bhanu.StockAnalyzer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${kafka.topics.ticks}")
    private String ticksTopic;

    @Value("${kafka.topics.aggregates}")
    private String aggregatesTopic;


    @Bean
    public NewTopic ticksTopic() {
        return TopicBuilder.name(ticksTopic)
                .build();
    }

    @Bean
    public NewTopic aggregatesTopic() {
        return TopicBuilder.name(aggregatesTopic)
                .build();
    }
}
