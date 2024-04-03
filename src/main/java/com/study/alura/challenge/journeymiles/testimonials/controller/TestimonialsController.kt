package com.study.alura.challenge.journeymiles.testimonials.controller

import com.study.alura.challenge.journeymiles.testimonials.dto.request.CreateOrUpdateTestimonialRequestDTO
import com.study.alura.challenge.journeymiles.testimonials.dto.response.TestimonialResponseDTO
import com.study.alura.challenge.journeymiles.testimonials.service.TestimonialsService
import jakarta.validation.Valid
import org.apache.logging.log4j.LogManager
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/testimonials")
class TestimonialsController(private val testimonialsService: TestimonialsService) {

    private val logger = LogManager.getLogger(this::class.java)

    @PostMapping
    fun create(
        @Valid @RequestBody createOrUpdateTestimonialRequestDTO: CreateOrUpdateTestimonialRequestDTO,
        @RequestParam(required = false, name = "user_id") userId: Long? = null
    ): ResponseEntity<TestimonialResponseDTO> {
        return ResponseEntity.status(HttpStatus.CREATED).body(
            testimonialsService.createTestimonial(createOrUpdateTestimonialRequestDTO, userId!!)
                .also { testimonialResponse ->
                    logger.info("Created a new testimonial with id ${testimonialResponse.id} for deponent: ${testimonialResponse.deponent}")
                })
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody createOrUpdateTestimonialRequestDTO: CreateOrUpdateTestimonialRequestDTO,
        @RequestParam(required = false, name = "user_id") userId: Long? = null
    ): ResponseEntity<TestimonialResponseDTO> {
        return ResponseEntity.ok(
            testimonialsService.updateTestimonial(createOrUpdateTestimonialRequestDTO, id, userId!!)
                .also { testimonialResponse ->
                    logger.info("Updated testimonial with id ${testimonialResponse.id} for deponent: ${testimonialResponse.deponent}")
                })
    }

    @GetMapping
    fun getTestimonials(pageable: Pageable): ResponseEntity<Page<TestimonialResponseDTO>> {
        return ResponseEntity.ok(testimonialsService.getTestimonials(pageable))
    }

    @GetMapping("/{id}")
    fun getTestimonialById(
        @PathVariable id: Long,
        @RequestParam(required = false, name = "user_id") userId: Long? = null
    ): ResponseEntity<TestimonialResponseDTO> {
        return ResponseEntity.ok(testimonialsService.getTestimonial(id, userId!!))
    }

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable id: Long,
        @RequestParam(required = false, name = "user_id") userId: Long? = null
    ): ResponseEntity<Void> =
        testimonialsService.deleteTestimonial(id, userId!!).let {
            ResponseEntity.noContent().build()
        }
}