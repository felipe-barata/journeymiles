package com.study.alura.challenge.journeymiles.pictures.repository

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.study.alura.challenge.journeymiles.model.entity.S3ObjectStorageEntity
import com.study.alura.challenge.journeymiles.model.enums.ErrorCodeEnum
import com.study.alura.challenge.journeymiles.model.exceptions.DomainException
import com.study.alura.challenge.journeymiles.model.exceptions.PictureNotFoundException
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Repository

@Repository
class AmazonDynamoDBRepository(private val amazonDynamoDB: DynamoDBMapper) {

    private val logger = LogManager.getLogger(this::class.java)

    fun insert(s3ObjectStorageEntity: S3ObjectStorageEntity) = runCatching {
        amazonDynamoDB.save(s3ObjectStorageEntity)
    }.onSuccess {
        logger.info(
            "Saved an object to DynamoDB with id ${s3ObjectStorageEntity.id} and type: ${s3ObjectStorageEntity.type}"
        )
    }.onFailure {
        raiseDomainException(
            "Could not save object to DynamoDB with id ${s3ObjectStorageEntity.id} and " +
                    "type: ${s3ObjectStorageEntity.type} with error: ${it.message}"
        )
    }

    fun update(s3ObjectStorageEntity: S3ObjectStorageEntity) = runCatching {
        amazonDynamoDB.load(S3ObjectStorageEntity::class.java, s3ObjectStorageEntity.id, s3ObjectStorageEntity.type)
            ?.let {
                val newEntity = it.copy(files = s3ObjectStorageEntity.files)
                amazonDynamoDB.save(newEntity)
            } ?: throw PictureNotFoundException(
            s3ObjectStorageEntity.id, s3ObjectStorageEntity.type
        )
    }.onSuccess {
        logger.info(
            "Updated an object in DynamoDB with id ${s3ObjectStorageEntity.id} and type: ${s3ObjectStorageEntity.type}"
        )
    }.onFailure {
        if (it is DomainException) {
            throw it
        } else {
            raiseDomainException(
                "Could not update object in DynamoDB with id ${s3ObjectStorageEntity.id} " +
                        "and type: ${s3ObjectStorageEntity.type} with error: ${it.message}"
            )
        }
    }

    fun delete(id: Long, type: String) = runCatching {
        amazonDynamoDB.load(S3ObjectStorageEntity::class.java, id, type)
            ?.let {
                amazonDynamoDB.delete(it)
            }
    }.onFailure {
        raiseDomainException("Could not delete object in DynamoDB with id $id and type: $type with error: ${it.message}")
    }

    fun select(id: Long, type: String) = runCatching {
        amazonDynamoDB.load(S3ObjectStorageEntity::class.java, id, type)
    }.onFailure {
        raiseDomainException("Could not query object in DynamoDB with id $id and type: $type with error: ${it.message}")
    }.getOrThrow()

    private fun raiseDomainException(message: String) {
        logger.error(message)
        throw DomainException(
            errorCodeEnum = ErrorCodeEnum.GENERIC_ERROR,
            message = message
        )
    }
}
