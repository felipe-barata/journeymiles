package com.study.alura.challenge.journeymiles.testimonials.service.impl

import com.study.alura.challenge.journeymiles.testimonials.mappers.toHomeResponse
import com.study.alura.challenge.journeymiles.testimonials.repository.TestimonialsRepository
import com.study.alura.challenge.journeymiles.testimonials.service.TestimonialsHomeService
import com.study.alura.challenge.journeymiles.testimonials.dto.response.TestimonialsHomeResponseDTO
import org.springframework.stereotype.Service

@Service
class TestimonialsHomeServiceImpl(private val testimonialsRepository: TestimonialsRepository) :
    TestimonialsHomeService {
    override fun getHome(): List<TestimonialsHomeResponseDTO> {
        return testimonialsRepository.getRandomTestimonials().toHomeResponse()
    }
}