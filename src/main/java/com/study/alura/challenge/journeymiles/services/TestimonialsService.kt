package com.study.alura.challenge.journeymiles.services

import com.study.alura.challenge.journeymiles.web.request.CreateTestimonialRequestDTO
import com.study.alura.challenge.journeymiles.web.request.UpdateTestimonialRequestDTO
import com.study.alura.challenge.journeymiles.web.response.TestimonialResponseDTO

interface TestimonialsService {

    fun createTestimonial(createTestimonialRequestDTO: CreateTestimonialRequestDTO) : TestimonialResponseDTO

    fun updateTestimonial(updateTestimonialRequestDTO: UpdateTestimonialRequestDTO, testimonialId: Long) : TestimonialResponseDTO

    fun getTestimonials(): List<TestimonialResponseDTO>

    fun deleteTestimonial(testimonialId: Long): Boolean
}