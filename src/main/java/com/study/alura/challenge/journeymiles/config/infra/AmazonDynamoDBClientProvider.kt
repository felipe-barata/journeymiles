package com.study.alura.challenge.journeymiles.config.infra

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.study.alura.challenge.journeymiles.config.vars.AmazonAWSVars
import com.study.alura.challenge.journeymiles.config.vars.AmazonDynamoDBVars
import org.apache.logging.log4j.LogManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class AmazonDynamoDBClientProvider(
    private val amazonDynamoDBVars: AmazonDynamoDBVars,
    private val amazonAWSVars: AmazonAWSVars
) {

    private val logger = LogManager.getLogger(AmazonDynamoDBClientProvider::class.java)

    @Bean(value = ["amazonDynamoDB", "dynamoDB"])
    @Primary
    fun createConfiguration(amazonAWSCredentials: AWSCredentials): DynamoDBMapper =
        DynamoDBMapper(
            AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(
                    AwsClientBuilder.EndpointConfiguration(amazonDynamoDBVars.endpoint, amazonAWSVars.region)
                )
                .withCredentials(AWSStaticCredentialsProvider(amazonAWSCredentials))
                .build()
        )

}
