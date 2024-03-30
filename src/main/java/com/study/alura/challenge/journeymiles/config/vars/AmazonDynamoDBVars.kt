package com.study.alura.challenge.journeymiles.config.vars

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
data class AmazonDynamoDBVars(
    @Value("\${config.aws.dynamodb.endpoint}") val endpoint: String,
)
