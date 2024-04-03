package com.study.alura.challenge.journeymiles.config.vars

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
data class PictureVars(
    @Value("\${config.variables.pictures.users-bucket-name}") val usersBucketName: String,
    @Value("\${config.variables.pictures.destinations-bucket-name}") val destinationsBucketName: String
)
