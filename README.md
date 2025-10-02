#📈 Real-Time Stock Analyzer

A Java Spring Boot project that demonstrates how to build a real-time stock price analyzer using:

✅ Apache Kafka – for event streaming

✅ Kafka Streams – for time-windowed aggregations (avg price, volume, VWAP)

✅ PostgreSQL/MySQL – to persist raw ticks & aggregates

✅ Redis – for caching latest ticks for fast reads

✅ WebSockets (STOMP/SockJS) – to push live updates to browser clients

✅ Docker Compose – to run Kafka, Zookeeper, Postgres, and Redis easily in dev

🚀 Features

Ingest real-time stock price ticks into Kafka

Persist raw tick data in Postgres for analytics

Compute 1-min, 5-min moving averages, VWAP, volume sums via Kafka Streams

Store recent prices in Redis for low-latency queries

Push live ticks & aggregates to clients using WebSockets

Provide REST APIs to fetch historical and aggregated data
