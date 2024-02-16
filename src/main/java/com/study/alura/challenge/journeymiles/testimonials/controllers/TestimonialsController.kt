package com.study.alura.challenge.journeymiles.testimonials.controllers

import com.study.alura.challenge.journeymiles.dtos.request.CreateTestimonialRequestDTO
import com.study.alura.challenge.journeymiles.dtos.request.UpdateTestimonialRequestDTO
import com.study.alura.challenge.journeymiles.dtos.response.TestimonialResponseDTO
import com.study.alura.challenge.journeymiles.testimonials.services.TestimonialsService
import org.apache.logging.log4j.LogManager
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/testimonials")
class TestimonialsController(private val testimonialsService: TestimonialsService) {

    private val logger = LogManager.getLogger(this::class.java)

    @PostMapping
    fun create(
        @ModelAttribute createTestimonialRequestDTO: CreateTestimonialRequestDTO
    ): ResponseEntity<TestimonialResponseDTO> {
        return ResponseEntity.ok(
            testimonialsService.createTestimonial(createTestimonialRequestDTO).also { testimonialResponse ->
                logger.info("Created a new testimonial with id ${testimonialResponse.id} for deponent: ${testimonialResponse.deponent}")
            })
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @ModelAttribute updateTestimonialRequestDTO: UpdateTestimonialRequestDTO
    ): ResponseEntity<TestimonialResponseDTO> {
        return ResponseEntity.ok(
            testimonialsService.updateTestimonial(updateTestimonialRequestDTO, id).also { testimonialResponse ->
                logger.info("Updated testimonial with id ${testimonialResponse.id} for deponent: ${testimonialResponse.deponent}")
            })
    }

    @GetMapping
    fun getTestimonials(): ResponseEntity<List<TestimonialResponseDTO>> {
        return ResponseEntity.ok(testimonialsService.getTestimonials())
    }

    @GetMapping("/{id}")
    fun getTestimonialById(@PathVariable id: Long): ResponseEntity<TestimonialResponseDTO> {
        return ResponseEntity.ok(testimonialsService.getTestimonial(id))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> = testimonialsService.deleteTestimonial(id).let {
        ResponseEntity.accepted().build()
    }
}