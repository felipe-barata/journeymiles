package com.study.alura.challenge.journeymiles.dtos.request

import java.io.Serializable
import org.springframework.web.multipart.MultipartFile

data class CreateTestimonialRequestDTO(
        val deponent: String,
        val testimonial: String,
        val picture: MultipartFile? = null
): Serializable
