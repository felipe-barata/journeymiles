package com.study.alura.challenge.journeymiles.services.impl

import com.study.alura.challenge.journeymiles.model.exceptions.TestimonialNotFoundException
import com.study.alura.challenge.journeymiles.repository.TestimonialsRepository
import com.study.alura.challenge.journeymiles.services.TestimonialsService
import com.study.alura.challenge.journeymiles.services.mappers.toEntity
import com.study.alura.challenge.journeymiles.services.mappers.toResponse
import com.study.alura.challenge.journeymiles.web.request.CreateTestimonialRequestDTO
import com.study.alura.challenge.journeymiles.web.request.UpdateTestimonialRequestDTO
import com.study.alura.challenge.journeymiles.web.response.TestimonialResponseDTO
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TestimonialsServiceImpl(
        private val testimonialsRepository: TestimonialsRepository
) : TestimonialsService {
    override fun createTestimonial(createTestimonialRequestDTO: CreateTestimonialRequestDTO): TestimonialResponseDTO {
        val entity = createTestimonialRequestDTO.toEntity()
        val savedEntity = testimonialsRepository.save(entity)
        return savedEntity.toResponse()
    }

    override fun updateTestimonial(updateTestimonialRequestDTO: UpdateTestimonialRequestDTO, testimonialId: Long): TestimonialResponseDTO {
        val updatedTestimonialEntity = testimonialsRepository.findByIdOrNull(testimonialId)?.let {
            val testimonialEntity = it.copy(testimonial = updateTestimonialRequestDTO.testimonial)
            testimonialsRepository.save(testimonialEntity)
        }
        return updatedTestimonialEntity?.toResponse()
                ?: throw TestimonialNotFoundException(testimonialId)
    }

    override fun getTestimonials(): List<TestimonialResponseDTO> = testimonialsRepository.findAll().toResponse()

    override fun deleteTestimonial(testimonialId: Long): Boolean {
        testimonialsRepository.findByIdOrNull(testimonialId)?.let {
            testimonialsRepository.delete(it)
            return true
        } ?: throw TestimonialNotFoundException(testimonialId)
    }
}