package com.study.alura.challenge.journeymiles.testimonials.mappers

import com.study.alura.challenge.journeymiles.model.entity.TestimonialEntity
import com.study.alura.challenge.journeymiles.model.entity.UserEntity
import com.study.alura.challenge.journeymiles.testimonials.dto.request.CreateOrUpdateTestimonialRequestDTO
import com.study.alura.challenge.journeymiles.testimonials.dto.response.TestimonialResponseDTO
import com.study.alura.challenge.journeymiles.testimonials.dto.response.TestimonialsHomeResponseDTO
import java.time.format.DateTimeFormatter

fun TestimonialEntity.toResponse() = TestimonialResponseDTO(
    id = this.id!!,
    deponent = this.deponent,
    testimonial = this.testimonial,
    createdAt = this.createdAt.format(DateTimeFormatter.ISO_DATE_TIME),
    updatedAt = this.updatedAt?.format(DateTimeFormatter.ISO_DATE_TIME)
)

fun CreateOrUpdateTestimonialRequestDTO.toEntity(userEntity: UserEntity) = TestimonialEntity(
    deponent = userEntity.name,
    testimonial = this.testimonial,
    user = userEntity
)

fun List<TestimonialEntity>.toResponse() = map {
    it.toResponse()
}

fun List<TestimonialEntity>.toHomeResponse() = map {
    TestimonialsHomeResponseDTO(
        deponent = it.deponent,
        testimonial = it.testimonial
    )
}