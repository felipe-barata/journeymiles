package com.study.alura.challenge.journeymiles.config.infra

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.BasicAWSCredentials
import com.study.alura.challenge.journeymiles.config.vars.AmazonAWSVars
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class AmazonAWSCredentialsProvider(private val awsVars: AmazonAWSVars) {

    @Bean(value = ["awsCredentials", "amazonAWSCredentials"])
    @Primary
    fun amazonAWSCredentials(): AWSCredentials = BasicAWSCredentials(
        awsVars.accessKey, awsVars.secretKey
    )

}