package com.study.alura.challenge.journeymiles.dtos.response

import java.io.Serializable

data class TestimonialsHomeResponseDTO(
    val deponent: String,
    val testimonial: String,
    val picture: String? = null
) : Serializable
