package com.study.alura.challenge.journeymiles.web.controllers

import com.study.alura.challenge.journeymiles.services.TestimonialsHomeService
import com.study.alura.challenge.journeymiles.web.response.TestimonialResponseDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("testimonials-home")
class TestimonialsHomeController(private val testimonialsHomeService: TestimonialsHomeService) {

    @GetMapping
    fun getHome(): ResponseEntity<List<TestimonialResponseDTO>> {
        return ResponseEntity.ok(testimonialsHomeService.getHome())
    }

}