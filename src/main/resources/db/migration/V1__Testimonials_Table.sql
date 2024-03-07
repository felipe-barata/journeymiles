CREATE TABLE IF NOT EXISTS testimonials(
    id BIGSERIAL,
    testimonial TEXT NOT NULL,
    deponent VARCHAR(100) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NULL
);