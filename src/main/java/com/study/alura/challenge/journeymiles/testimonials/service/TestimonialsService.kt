package com.study.alura.challenge.journeymiles.testimonials.service

import com.study.alura.challenge.journeymiles.testimonials.dto.request.CreateOrUpdateTestimonialRequestDTO
import com.study.alura.challenge.journeymiles.testimonials.dto.response.TestimonialResponseDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface TestimonialsService {

    fun createTestimonial(
        createTestimonialRequestDTO: CreateOrUpdateTestimonialRequestDTO,
        userId: Long
    ): TestimonialResponseDTO

    fun updateTestimonial(
        createOrUpdateTestimonialRequestDTO: CreateOrUpdateTestimonialRequestDTO,
        testimonialId: Long,
        userId: Long
    ): TestimonialResponseDTO

    fun getTestimonials(pageable: Pageable): Page<TestimonialResponseDTO>

    fun getTestimonial(id: Long, userId: Long): TestimonialResponseDTO

    fun deleteTestimonial(testimonialId: Long, userId: Long): Boolean
}