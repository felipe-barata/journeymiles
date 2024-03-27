package com.study.alura.challenge.journeymiles.model.entity

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable

@DynamoDBTable(tableName = "s3_object_storage_table")
data class S3ObjectStorageEntity(
    @DynamoDBHashKey(attributeName = "id")
    val id: Long,
    @DynamoDBRangeKey(attributeName = "type")
    val type: String,
    @DynamoDBAttribute
    val files: Set<String>
)
