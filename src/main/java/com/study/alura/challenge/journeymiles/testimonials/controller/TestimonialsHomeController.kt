package com.study.alura.challenge.journeymiles.testimonials.controller

import com.study.alura.challenge.journeymiles.testimonials.dto.response.TestimonialsHomeResponseDTO
import com.study.alura.challenge.journeymiles.testimonials.service.TestimonialsHomeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("testimonials-home")
class TestimonialsHomeController(private val testimonialsHomeService: TestimonialsHomeService) {

    @GetMapping
    fun getHome(): ResponseEntity<List<TestimonialsHomeResponseDTO>> {
        return ResponseEntity.ok(testimonialsHomeService.getHome())
    }

}