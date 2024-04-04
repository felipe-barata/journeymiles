package com.study.alura.challenge.journeymiles.testimonials.service.impl

import com.study.alura.challenge.journeymiles.pictures.service.GetPicturesService
import com.study.alura.challenge.journeymiles.testimonials.dto.response.TestimonialsHomeResponseDTO
import com.study.alura.challenge.journeymiles.testimonials.mappers.toHomeResponse
import com.study.alura.challenge.journeymiles.testimonials.repository.TestimonialsRepository
import com.study.alura.challenge.journeymiles.testimonials.service.TestimonialsHomeService
import org.springframework.stereotype.Service

@Service
class TestimonialsHomeServiceImpl(
    private val testimonialsRepository: TestimonialsRepository,
    private val picturesService: GetPicturesService
) :
    TestimonialsHomeService {
    override fun getHome(): List<TestimonialsHomeResponseDTO> {
        return testimonialsRepository.getRandomTestimonials().map {
            it.toHomeResponse(picturesService.getUserProfilePicture(it.user?.id!!)?.pictures ?: emptySet())
        }
    }
}