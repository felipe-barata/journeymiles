package com.study.alura.challenge.journeymiles.testimonials.controller

import com.study.alura.challenge.journeymiles.model.exceptions.UsernameNotProvidedException
import com.study.alura.challenge.journeymiles.security.service.AuthenticationFacade
import com.study.alura.challenge.journeymiles.testimonials.dto.request.CreateOrUpdateTestimonialRequestDTO
import com.study.alura.challenge.journeymiles.testimonials.dto.response.TestimonialResponseDTO
import com.study.alura.challenge.journeymiles.testimonials.service.TestimonialsService
import jakarta.validation.Valid
import org.apache.logging.log4j.LogManager
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/testimonials")
class TestimonialsController(
    private val testimonialsService: TestimonialsService,
    private val authenticationFacade: AuthenticationFacade
) {

    private val logger = LogManager.getLogger(this::class.java)

    @PostMapping
    @Secured("ROLE_CUSTOMER")
    fun create(
        @Valid @RequestBody createOrUpdateTestimonialRequestDTO: CreateOrUpdateTestimonialRequestDTO
    ): ResponseEntity<TestimonialResponseDTO> {
        return ResponseEntity.status(HttpStatus.CREATED).body(
            testimonialsService.createTestimonial(
                createOrUpdateTestimonialRequestDTO,
                authenticationFacade.getAuthenticatedUser() ?: throw UsernameNotProvidedException()
            )
                .also { testimonialResponse ->
                    logger.info("Created a new testimonial with id ${testimonialResponse.id} for deponent: ${testimonialResponse.deponent}")
                })
    }

    @PutMapping("/{id}")
    @Secured("ROLE_CUSTOMER")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody createOrUpdateTestimonialRequestDTO: CreateOrUpdateTestimonialRequestDTO
    ): ResponseEntity<TestimonialResponseDTO> {
        return ResponseEntity.ok(
            testimonialsService.updateTestimonial(
                createOrUpdateTestimonialRequestDTO,
                id,
                authenticationFacade.getAuthenticatedUser() ?: throw UsernameNotProvidedException()
            )
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
        @PathVariable id: Long
    ): ResponseEntity<TestimonialResponseDTO> {
        return ResponseEntity.ok(
            testimonialsService.getTestimonial(
                id,
                authenticationFacade.getAuthenticatedUser() ?: throw UsernameNotProvidedException()
            )
        )
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_CUSTOMER")
    fun delete(
        @PathVariable id: Long
    ): ResponseEntity<Void> =
        testimonialsService.deleteTestimonial(
            id,
            authenticationFacade.getAuthenticatedUser() ?: throw UsernameNotProvidedException()
        ).let {
            ResponseEntity.noContent().build()
        }
}