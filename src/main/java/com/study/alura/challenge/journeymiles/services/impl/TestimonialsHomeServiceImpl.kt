package com.study.alura.challenge.journeymiles.services.impl

import com.study.alura.challenge.journeymiles.repository.TestimonialsRepository
import com.study.alura.challenge.journeymiles.services.TestimonialsHomeService
import com.study.alura.challenge.journeymiles.services.mappers.toResponse
import com.study.alura.challenge.journeymiles.web.response.TestimonialResponseDTO
import org.springframework.stereotype.Service

@Service
class TestimonialsHomeServiceImpl(private val testimonialsRepository: TestimonialsRepository) : TestimonialsHomeService {
    override fun getHome(): List<TestimonialResponseDTO> {
        return testimonialsRepository.getRandomTestimonials().toResponse()
    }
}