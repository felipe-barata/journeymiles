package com.study.alura.challenge.journeymiles.entity

import jakarta.persistence.*
import java.time.OffsetDateTime

@Entity(name = "testimonials")
data class TestimonialEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val picture: String? = null,
        val testimonial: String,
        val deponent: String,
        @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
        val createdAt: OffsetDateTime = OffsetDateTime.now(),
        @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
        val updatedAt: OffsetDateTime? = null
)