package com.study.alura.challenge.journeymiles.web.request

import java.io.Serializable

data class CreateTestimonialRequestDTO(
        val deponent: String,
        val testimonial: String
): Serializable
