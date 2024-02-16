package com.study.alura.challenge.journeymiles.testimonials.services

import com.study.alura.challenge.journeymiles.dtos.response.TestimonialsHomeResponseDTO

interface TestimonialsHomeService {

    fun getHome(): List<TestimonialsHomeResponseDTO>

}