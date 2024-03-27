package com.study.alura.challenge.journeymiles.config.vars

import org.springframework.beans.factory.annotation.Value

data class DynamoDBVars(@Value("\${config.aws.region}") val region: String)
