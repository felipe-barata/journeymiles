package com.study.alura.challenge.journeymiles.testimonials.dto.response

import java.io.Serializable

data class TestimonialResponseDTO(
        val id: Long,
        val deponent: String,
        val testimonial: String,
        val createdAt: String,
        val updatedAt: String? = null,
        val pictures: Set<String> = emptySet()
): Serializable
