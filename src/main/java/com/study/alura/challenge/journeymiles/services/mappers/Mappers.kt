package com.study.alura.challenge.journeymiles.services.mappers

import com.study.alura.challenge.journeymiles.entity.TestimonialEntity
import com.study.alura.challenge.journeymiles.web.request.CreateTestimonialRequestDTO
import com.study.alura.challenge.journeymiles.web.response.TestimonialResponseDTO
import java.time.format.DateTimeFormatter

fun CreateTestimonialRequestDTO.toEntity() = TestimonialEntity(
        //picture = this.picture,
        deponent = this.deponent,
        testimonial = this.testimonial
)

fun TestimonialEntity.toResponse() = TestimonialResponseDTO(
        id = this.id!!,
        deponent = this.deponent,
        testimonial = this.testimonial,
        picture = this.picture,
        createdAt = this.createdAt.format(DateTimeFormatter.ISO_DATE_TIME),
        updatedAt = this.updatedAt?.format(DateTimeFormatter.ISO_DATE_TIME)
)

fun List<TestimonialEntity>.toResponse() = map {
    it.toResponse()
}