package com.study.alura.challenge.journeymiles.config.vars

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
data class AmazonS3Vars(
        @Value("\${config.aws.s3_bucket_name}") val bucketName: String,
        @Value("\${config.aws.region}") val region: String,
        @Value("\${config.aws.s3_endpoint}") val endpoint: String
)
