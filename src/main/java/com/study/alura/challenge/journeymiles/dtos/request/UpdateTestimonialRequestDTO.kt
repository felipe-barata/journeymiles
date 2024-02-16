package com.study.alura.challenge.journeymiles.dtos.request

import java.io.Serializable
import org.springframework.web.multipart.MultipartFile

data class UpdateTestimonialRequestDTO(
    val testimonial: String,
    val picture: MultipartFile? = null
) : Serializable
