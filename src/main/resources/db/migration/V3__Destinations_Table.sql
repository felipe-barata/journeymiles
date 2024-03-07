CREATE TABLE IF NOT EXISTS destinations(
    id BIGSERIAL,
    name VARCHAR(200) NOT NULL,
    price DECIMAL(16,2) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NULL
);
