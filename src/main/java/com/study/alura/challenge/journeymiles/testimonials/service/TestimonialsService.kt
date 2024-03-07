package com.study.alura.challenge.journeymiles.testimonials.service

import com.study.alura.challenge.journeymiles.testimonials.dto.request.CreateTestimonialRequestDTO
import com.study.alura.challenge.journeymiles.testimonials.dto.request.UpdateTestimonialRequestDTO
import com.study.alura.challenge.journeymiles.testimonials.dto.response.TestimonialResponseDTO

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