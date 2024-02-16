package com.study.alura.challenge.journeymiles.dtos.response

import java.io.Serializable

data class TestimonialResponseDTO(
        val id: Long,
        val deponent: String,
        val testimonial: String,
        val createdAt: String,
        val updatedAt: String? = null,
        val picture: String? = null
): Serializable
