package com.study.alura.challenge.journeymiles.testimonials.dto.request

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.io.Serializable
import org.springframework.web.multipart.MultipartFile

data class UpdateTestimonialRequestDTO(
    @field:NotNull(message = "The testimonial field is mandatory")
    @field:Size(min = 10, max = 500, message = "The testimonial field must bet between 10 and 500 chars")
    val testimonial: String,
    val picture: MultipartFile? = null
) : Serializable
