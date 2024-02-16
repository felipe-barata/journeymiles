package com.study.alura.challenge.journeymiles.testimonials.repository

import com.study.alura.challenge.journeymiles.utils.AmazonS3Utils
import com.study.alura.challenge.journeymiles.utils.FileUtils
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Repository
import org.springframework.web.multipart.MultipartFile

@Repository
class AmazonS3TestimonialsRepository(
    private val amazonS3: AmazonS3Utils, private val fileUtils: FileUtils
) {

    private val logger = LogManager.getLogger(this::class.java)

    fun createTestimonialPicture(fileName: String, bucket: String, file: MultipartFile) {
        logger.info("create new testimonial picture with name: $fileName")
        amazonS3.createFile(bucket, fileName, fileUtils.convertMultipartToFile(file))
    }

    fun updateTestimonialPicture(fileName: String, bucket: String, file: MultipartFile) {
        logger.info("update testimonial picture with name: $file")
        amazonS3.updateFile(bucket, fileName, fileUtils.convertMultipartToFile(file))
    }

    fun deletePicture(fileName: String, bucket: String) {
        logger.info("Removing a picture for testimonial with name: $fileName")
        amazonS3.deleteFile(bucket, fileName)
    }

    fun doesPictureExist(fileName: String, bucket: String) = amazonS3.doesObjectExist(bucket, fileName)

}