package com.study.alura.challenge.journeymiles.testimonials.services.impl

import com.study.alura.challenge.journeymiles.dtos.mappers.toHomeResponse
import com.study.alura.challenge.journeymiles.testimonials.repository.TestimonialsRepository
import com.study.alura.challenge.journeymiles.testimonials.services.TestimonialsHomeService
import com.study.alura.challenge.journeymiles.dtos.mappers.toResponse
import com.study.alura.challenge.journeymiles.dtos.response.TestimonialResponseDTO
import com.study.alura.challenge.journeymiles.dtos.response.TestimonialsHomeResponseDTO
import org.springframework.stereotype.Service

@Service
class TestimonialsHomeServiceImpl(private val testimonialsRepository: TestimonialsRepository) :
    TestimonialsHomeService {
    override fun getHome(): List<TestimonialsHomeResponseDTO> {
        return testimonialsRepository.getRandomTestimonials().toHomeResponse()
    }
}