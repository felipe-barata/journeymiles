package com.study.alura.challenge.journeymiles.repository

import com.study.alura.challenge.journeymiles.entity.TestimonialEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TestimonialsRepository: JpaRepository<TestimonialEntity, Long> {

    @Query("SELECT * FROM testimonials ORDER BY random() LIMIT 3", nativeQuery = true)
    fun getRandomTestimonials(): List<TestimonialEntity>

}