package com.study.alura.challenge.journeymiles.testimonials.services

import com.study.alura.challenge.journeymiles.dtos.request.CreateTestimonialRequestDTO
import com.study.alura.challenge.journeymiles.dtos.request.UpdateTestimonialRequestDTO
import com.study.alura.challenge.journeymiles.dtos.response.TestimonialResponseDTO

interface TestimonialsService {

    fun createTestimonial(createTestimonialRequestDTO: CreateTestimonialRequestDTO): TestimonialResponseDTO

    fun updateTestimonial(
        updateTestimonialRequestDTO: UpdateTestimonialRequestDTO,
        testimonialId: Long
    ): TestimonialResponseDTO

    fun getTestimonials(): List<TestimonialResponseDTO>

    fun getTestimonial(id: Long): TestimonialResponseDTO

    fun deleteTestimonial(testimonialId: Long): Boolean
}