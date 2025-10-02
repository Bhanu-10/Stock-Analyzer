CREATE TABLE stock_price_history (
    id SERIAL PRIMARY KEY,
    symbol VARCHAR(20) NOT NULL,
    price NUMERIC(12, 4) NOT NULL,
    volume BIGINT NOT NULL,
    timestamp TIMESTAMP WITH TIME ZONE NOT NULL
);



ALTER DATABASE stockdb SET timezone TO 'Asia/Kolkata';



CREATE TABLE aggregate (
    id BIGSERIAL PRIMARY KEY,
    symbol VARCHAR(50) NOT NULL,
    avg_price DOUBLE PRECISION NOT NULL,
    total_volume BIGINT NOT NULL,
    tick_count BIGINT NOT NULL,
    window_start TIMESTAMP NOT NULL,
    window_end TIMESTAMP NOT NULL
);
