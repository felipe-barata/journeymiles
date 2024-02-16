package com.study.alura.challenge.journeymiles.testimonials.services.impl

import com.study.alura.challenge.journeymiles.config.vars.TestimonialsVars
import com.study.alura.challenge.journeymiles.dtos.mappers.toEntity
import com.study.alura.challenge.journeymiles.dtos.mappers.toResponse
import com.study.alura.challenge.journeymiles.dtos.request.CreateTestimonialRequestDTO
import com.study.alura.challenge.journeymiles.dtos.request.UpdateTestimonialRequestDTO
import com.study.alura.challenge.journeymiles.dtos.response.TestimonialResponseDTO
import com.study.alura.challenge.journeymiles.model.exceptions.TestimonialNotFoundException
import com.study.alura.challenge.journeymiles.testimonials.repository.AmazonS3TestimonialsRepository
import com.study.alura.challenge.journeymiles.testimonials.repository.TestimonialsRepository
import com.study.alura.challenge.journeymiles.testimonials.services.TestimonialsService
import java.time.OffsetDateTime
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TestimonialsServiceImpl(
    private val testimonialsRepository: TestimonialsRepository,
    private val amazonS3Repository: AmazonS3TestimonialsRepository,
    private val testimonialsVars: TestimonialsVars
) : TestimonialsService {
    override fun createTestimonial(createTestimonialRequestDTO: CreateTestimonialRequestDTO): TestimonialResponseDTO {
        val entity = createTestimonialRequestDTO.toEntity()
        val savedEntity = testimonialsRepository.save(entity)
        createTestimonialRequestDTO.picture?.let { picture ->
            val key = buildTestimonialKey(savedEntity.id!!)
            amazonS3Repository.createTestimonialPicture(
                key,
                testimonialsVars.bucketName,
                picture
            )
            savedEntity.picture = key
            testimonialsRepository.save(savedEntity)
        }
        return savedEntity.toResponse()
    }

    override fun updateTestimonial(
        updateTestimonialRequestDTO: UpdateTestimonialRequestDTO, testimonialId: Long
    ): TestimonialResponseDTO {
        val updatedTestimonialEntity = testimonialsRepository.findByIdOrNull(testimonialId)?.let {
            updateTestimonialRequestDTO.picture?.let { picture ->
                val key = buildTestimonialKey(testimonialId)
                amazonS3Repository.updateTestimonialPicture(
                    key,
                    testimonialsVars.bucketName,
                    picture
                )
                it.picture = key
            }
            val testimonialEntity =
                it.copy(testimonial = updateTestimonialRequestDTO.testimonial, updatedAt = OffsetDateTime.now())
            testimonialsRepository.save(testimonialEntity)
        }
        return updatedTestimonialEntity?.toResponse() ?: throw TestimonialNotFoundException(testimonialId)
    }

    override fun getTestimonials(): List<TestimonialResponseDTO> = testimonialsRepository.findAll().toResponse()
    override fun getTestimonial(id: Long): TestimonialResponseDTO {
        return testimonialsRepository.findByIdOrNull(id)?.toResponse() ?: throw TestimonialNotFoundException(id)
    }

    override fun deleteTestimonial(testimonialId: Long): Boolean {
        testimonialsRepository.findByIdOrNull(testimonialId)?.let {
            testimonialsRepository.delete(it)
            amazonS3Repository.deletePicture(buildTestimonialKey(testimonialId), testimonialsVars.bucketName)
            return true
        } ?: throw TestimonialNotFoundException(testimonialId)
    }

    private fun buildTestimonialKey(id: Long) = "${testimonialsVars.filePrefix}$id"
}