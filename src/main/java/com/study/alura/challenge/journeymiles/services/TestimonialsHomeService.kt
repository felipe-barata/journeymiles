package com.study.alura.challenge.journeymiles.services

import com.study.alura.challenge.journeymiles.repository.TestimonialsRepository
import com.study.alura.challenge.journeymiles.web.response.TestimonialResponseDTO

interface TestimonialsHomeService {

    fun getHome(): List<TestimonialResponseDTO>

}