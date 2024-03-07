package com.study.alura.challenge.journeymiles.testimonials.service

import com.study.alura.challenge.journeymiles.testimonials.dto.response.TestimonialsHomeResponseDTO

interface TestimonialsHomeService {

    fun getHome(): List<TestimonialsHomeResponseDTO>

}