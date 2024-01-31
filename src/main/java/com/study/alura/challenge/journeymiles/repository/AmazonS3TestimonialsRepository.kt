package com.study.alura.challenge.journeymiles.repository

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.PutObjectRequest
import com.study.alura.challenge.journeymiles.config.vars.AmazonS3Vars
import java.io.File
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Component

@Component
class AmazonS3TestimonialsRepository(
    private val amazonS3: AmazonS3Client,
    private val amazonS3Vars: AmazonS3Vars
) {

    private val logger = LogManager.getLogger(this::class.java)

    fun uploadTestimonialPicture(id: Long, file: File) {
        logger.info("Uploading a picture for testimonial with id: $id")
        val putObjectRequest = PutObjectRequest(amazonS3Vars.bucketName, "", file)
        amazonS3.putObject(putObjectRequest)
    }

}