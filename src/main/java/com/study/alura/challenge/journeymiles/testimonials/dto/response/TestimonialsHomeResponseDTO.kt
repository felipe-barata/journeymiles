package com.study.alura.challenge.journeymiles.testimonials.dto.response

import java.io.Serializable

data class TestimonialsHomeResponseDTO(
    val deponent: String,
    val testimonial: String,
    val pictures: Set<String> = emptySet()
) : Serializable
