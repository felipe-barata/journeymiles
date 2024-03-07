package com.study.alura.challenge.journeymiles.testimonials.dto.request

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.io.Serializable
import org.springframework.web.multipart.MultipartFile

data class CreateTestimonialRequestDTO(
    @NotNull(message = "The deponent field is mandatory")
    @Size(min = 3, max = 100, message = "The deponent field must bet between 3 and 100 chars")
    val deponent: String,
    @NotNull(message = "The testimonial field is mandatory")
    @Size(min = 10, max = 500, message = "The testimonial field must bet between 10 and 500 chars")
    val testimonial: String,
    val picture: MultipartFile? = null
) : Serializable
