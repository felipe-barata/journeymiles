package com.study.alura.challenge.journeymiles.testimonials.service

import com.study.alura.challenge.journeymiles.testimonials.dto.request.CreateOrUpdateTestimonialRequestDTO
import com.study.alura.challenge.journeymiles.testimonials.dto.response.TestimonialResponseDTO
import com.study.alura.challenge.journeymiles.user.dto.response.UserDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface TestimonialsService {

    fun createTestimonial(
        createTestimonialRequestDTO: CreateOrUpdateTestimonialRequestDTO,
        userDTO: UserDTO
    ): TestimonialResponseDTO

    fun updateTestimonial(
        createOrUpdateTestimonialRequestDTO: CreateOrUpdateTestimonialRequestDTO,
        testimonialId: Long,
        userDTO: UserDTO
    ): TestimonialResponseDTO

    fun getTestimonials(pageable: Pageable): Page<TestimonialResponseDTO>

    fun getTestimonial(id: Long, userDTO: UserDTO): TestimonialResponseDTO

    fun deleteTestimonial(testimonialId: Long, userDTO: UserDTO): Boolean
}