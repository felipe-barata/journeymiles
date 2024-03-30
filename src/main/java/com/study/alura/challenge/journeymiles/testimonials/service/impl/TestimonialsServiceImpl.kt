package com.study.alura.challenge.journeymiles.testimonials.service.impl

import com.study.alura.challenge.journeymiles.model.exceptions.InvalidUserForTestimonialException
import com.study.alura.challenge.journeymiles.model.exceptions.TestimonialNotFoundException
import com.study.alura.challenge.journeymiles.testimonials.dto.request.CreateOrUpdateTestimonialRequestDTO
import com.study.alura.challenge.journeymiles.testimonials.dto.response.TestimonialResponseDTO
import com.study.alura.challenge.journeymiles.testimonials.mappers.toEntity
import com.study.alura.challenge.journeymiles.testimonials.mappers.toResponse
import com.study.alura.challenge.journeymiles.testimonials.repository.TestimonialsRepository
import com.study.alura.challenge.journeymiles.testimonials.service.TestimonialsService
import com.study.alura.challenge.journeymiles.user.mappers.toEntity
import com.study.alura.challenge.journeymiles.user.service.UserService
import java.time.OffsetDateTime
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TestimonialsServiceImpl(
    private val testimonialsRepository: TestimonialsRepository,
    private val userService: UserService
) : TestimonialsService {
    override fun createTestimonial(
        createTestimonialRequestDTO: CreateOrUpdateTestimonialRequestDTO,
        userId: Long
    ): TestimonialResponseDTO {
        val user = userService.findUser(userId)
        val entity = createTestimonialRequestDTO.toEntity(user.toEntity())
        return testimonialsRepository.save(entity).toResponse()
    }

    override fun updateTestimonial(
        createOrUpdateTestimonialRequestDTO: CreateOrUpdateTestimonialRequestDTO, testimonialId: Long, userId: Long
    ): TestimonialResponseDTO {
        val updatedTestimonialEntity = testimonialsRepository.findByIdOrNull(testimonialId)?.let {
            isUserAllowedToManageTestimonial(userService.findUser(userId).id, it.user?.id, testimonialId)
            val testimonialEntity =
                it.copy(testimonial = createOrUpdateTestimonialRequestDTO.testimonial, updatedAt = OffsetDateTime.now())
            testimonialsRepository.save(testimonialEntity)
        }
        return updatedTestimonialEntity?.toResponse() ?: throw TestimonialNotFoundException(testimonialId)
    }

    override fun getTestimonials(pageable: Pageable): Page<TestimonialResponseDTO> =
        testimonialsRepository.findAll(pageable).map { it.toResponse() }

    override fun getTestimonial(id: Long, userId: Long): TestimonialResponseDTO {
        return testimonialsRepository.findByIdOrNull(id)?.let {
            isUserAllowedToManageTestimonial(userService.findUser(userId).id, it.user?.id, it.id!!)
            it.toResponse()
        } ?: throw TestimonialNotFoundException(id)
    }

    override fun deleteTestimonial(testimonialId: Long, userId: Long): Boolean {
        testimonialsRepository.findByIdOrNull(testimonialId)?.let {
            isUserAllowedToManageTestimonial(userService.findUser(userId).id, it.user?.id, testimonialId)
            testimonialsRepository.delete(it)
            return true
        } ?: throw TestimonialNotFoundException(testimonialId)
    }

    private fun isUserAllowedToManageTestimonial(userId: Long, testimonialUserId: Long?, testimonialId: Long) {
        takeIf { testimonialUserId?.compareTo(userId) != 0 }?.let {
            throw InvalidUserForTestimonialException(
                userId = userId,
                testimonialId = testimonialId
            )
        }
    }
}