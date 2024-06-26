package com.study.alura.challenge.journeymiles.config.infra

import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.study.alura.challenge.journeymiles.config.vars.AmazonAWSVars
import com.study.alura.challenge.journeymiles.config.vars.AmazonS3Vars
import org.apache.logging.log4j.LogManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class AmazonS3ClientProvider(
    private val s3Vars: AmazonS3Vars,
    private val awsVars: AmazonAWSVars
) {

    private val logger = LogManager.getLogger(AmazonS3ClientProvider::class.java)

    @Bean(value = ["amazonS3"])
    @Primary
    fun createConfiguration(): AmazonS3 = AmazonS3ClientBuilder.standard().withEndpointConfiguration(
        AwsClientBuilder.EndpointConfiguration(s3Vars.endpoint, awsVars.region)
    ).withPathStyleAccessEnabled(true)
        .build().also {
            logger.info("Finished Amazon S3 Configuration")
        }

}