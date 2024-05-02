package com.study.alura.challenge.journeymiles.testimonials.service.impl

import com.study.alura.challenge.journeymiles.model.exceptions.InvalidUserForTestimonialException
import com.study.alura.challenge.journeymiles.model.exceptions.TestimonialNotFoundException
import com.study.alura.challenge.journeymiles.pictures.service.PicturesService
import com.study.alura.challenge.journeymiles.testimonials.dto.request.CreateOrUpdateTestimonialRequestDTO
import com.study.alura.challenge.journeymiles.testimonials.dto.response.TestimonialResponseDTO
import com.study.alura.challenge.journeymiles.testimonials.mappers.toEntity
import com.study.alura.challenge.journeymiles.testimonials.mappers.toResponse
import com.study.alura.challenge.journeymiles.testimonials.repository.TestimonialsRepository
import com.study.alura.challenge.journeymiles.testimonials.service.TestimonialsService
import com.study.alura.challenge.journeymiles.user.dto.response.UserDTO
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
    private val userService: UserService,
    private val picturesService: PicturesService
) : TestimonialsService {
    override fun createTestimonial(
        createTestimonialRequestDTO: CreateOrUpdateTestimonialRequestDTO,
        userDTO: UserDTO
    ): TestimonialResponseDTO {
        val entity = createTestimonialRequestDTO.toEntity(userDTO.toEntity())
        return testimonialsRepository.save(entity).toResponse()
    }

    override fun updateTestimonial(
        createOrUpdateTestimonialRequestDTO: CreateOrUpdateTestimonialRequestDTO, testimonialId: Long, userDTO: UserDTO
    ): TestimonialResponseDTO {
        val testimonial = getTestimonialFromDatabase(testimonialId)
        isUserAllowedToManageTestimonial(userDTO.id, testimonial.user?.id, testimonialId)
        val testimonialEntity =
            testimonial.copy(
                testimonial = createOrUpdateTestimonialRequestDTO.testimonial,
                updatedAt = OffsetDateTime.now()
            )
        return testimonialsRepository.save(testimonialEntity).toResponse()
    }

    override fun getTestimonials(pageable: Pageable): Page<TestimonialResponseDTO> =
        testimonialsRepository.findAll(pageable).map {
            it.toResponse(picturesService.getUserProfilePicture(it.user?.id!!)?.pictures ?: emptySet())
        }

    override fun getTestimonial(id: Long, userDTO: UserDTO): TestimonialResponseDTO {
        val testimonial = getTestimonialFromDatabase(id)
        isUserAllowedToManageTestimonial(userDTO.id, testimonial.user?.id, id)
        return testimonial.toResponse(picturesService.getUserProfilePicture(userDTO.id)?.pictures ?: emptySet())
    }

    override fun deleteTestimonial(testimonialId: Long, userDTO: UserDTO): Boolean {
        val testimonial = getTestimonialFromDatabase(testimonialId)
        isUserAllowedToManageTestimonial(userDTO.id, testimonial.user?.id, testimonialId)
        testimonialsRepository.delete(testimonial)
        return true
    }

    private fun isUserAllowedToManageTestimonial(userId: Long, testimonialUserId: Long?, testimonialId: Long) {
        takeIf { testimonialUserId?.compareTo(userId) != 0 }?.let {
            throw InvalidUserForTestimonialException(
                userId = userId,
                testimonialId = testimonialId
            )
        }
    }

    private fun getTestimonialFromDatabase(testimonialId: Long) =
        testimonialsRepository.findByIdOrNull(testimonialId) ?: throw TestimonialNotFoundException(testimonialId)
}