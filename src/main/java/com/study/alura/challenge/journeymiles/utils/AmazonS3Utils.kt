package com.study.alura.challenge.journeymiles.utils

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.DeleteObjectRequest
import com.amazonaws.services.s3.model.PutObjectRequest
import java.io.File
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Component

@Component
class AmazonS3Utils(
    private val amazonS3: AmazonS3
) {

    private val logger = LogManager.getLogger(this::class.java)

    fun createFile(bucket: String, key: String, file: File) {
        putObject(bucket, key, file)
    }

    fun updateFile(bucket: String, key: String, file: File) {
        deleteFile(bucket, key)
        putObject(bucket, key, file)
    }

    fun deleteFile(bucket: String, key: String) {
        logger.info("deleting an object with path: $bucket/$key")
        takeIf { doesObjectExist(bucket, key) }?.let {
            val deleteObjectRequest = DeleteObjectRequest(bucket, key)
            amazonS3.deleteObject(deleteObjectRequest)
        }
    }

    fun doesObjectExist(bucket: String, key: String) = amazonS3.doesObjectExist(bucket, key)

    private fun putObject(bucket: String, key: String, file: File) {
        logger.info("putting a new object with path: $bucket/$key")
        val putObjectRequest = PutObjectRequest(
            bucket, key, file
        ).withCannedAcl(CannedAccessControlList.BucketOwnerRead)
        amazonS3.putObject(putObjectRequest)
    }

}