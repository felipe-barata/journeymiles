package com.study.alura.challenge.journeymiles.config.vars

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
data class TestimonialsVars(
    @Value("\${config.variables.testimonials_s3_bucket_name}") val bucketName: String,
    @Value("\${config.variables.testimonials_prefix}") val filePrefix: String
)
