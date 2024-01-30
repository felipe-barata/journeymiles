DROP TABLE testimonials;

CREATE TABLE IF NOT EXISTS testimonials(
    id BIGSERIAL PRIMARY KEY,
    picture VARCHAR(200) DEFAULT NULL,
    testimonial TEXT NOT NULL,
    deponent VARCHAR(100) NOT NULL,
    created_at TIMESTAMP NOT NULL
);