package com.study.alura.challenge.journeymiles.dtos.mappers

import com.study.alura.challenge.journeymiles.dtos.request.CreateTestimonialRequestDTO
import com.study.alura.challenge.journeymiles.dtos.response.TestimonialResponseDTO
import com.study.alura.challenge.journeymiles.dtos.response.TestimonialsHomeResponseDTO
import com.study.alura.challenge.journeymiles.model.entity.TestimonialEntity
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

fun List<TestimonialEntity>.toHomeResponse() = map {
    TestimonialsHomeResponseDTO(
        deponent = it.deponent,
        testimonial = it.testimonial,
        picture = it.picture
    )
}