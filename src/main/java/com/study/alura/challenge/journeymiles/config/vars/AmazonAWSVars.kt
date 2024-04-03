package com.study.alura.challenge.journeymiles.config.vars

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
data class AmazonAWSVars(
    @Value("\${config.aws.region}") val region: String,
    @Value("\${config.aws.accesskey}") val accessKey: String,
    @Value("\${config.aws.secretkey}") val secretKey: String,
)
