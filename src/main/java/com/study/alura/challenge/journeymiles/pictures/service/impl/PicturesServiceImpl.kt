package com.study.alura.challenge.journeymiles.pictures.service.impl

import com.study.alura.challenge.journeymiles.config.vars.PictureVars
import com.study.alura.challenge.journeymiles.model.entity.S3ObjectStorageEntity
import com.study.alura.challenge.journeymiles.model.enums.PictureTypeEnum
import com.study.alura.challenge.journeymiles.model.enums.PictureTypeEnum.DESTINATION
import com.study.alura.challenge.journeymiles.model.enums.PictureTypeEnum.USER
import com.study.alura.challenge.journeymiles.pictures.dto.response.PictureResponseDTO
import com.study.alura.challenge.journeymiles.pictures.mappers.toDTO
import com.study.alura.challenge.journeymiles.pictures.repository.AmazonDynamoDBRepository
import com.study.alura.challenge.journeymiles.pictures.repository.AmazonS3Repository
import com.study.alura.challenge.journeymiles.pictures.service.PicturesService
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class PicturesServiceImpl(
    private val picturesVars: PictureVars,
    private val amazonDynamoDBRepository: AmazonDynamoDBRepository,
    private val amazonS3Repository: AmazonS3Repository
) : PicturesService {
    override fun saveUserProfilePicture(picture: MultipartFile, userId: Long) =
        savePictures(USER, userId, setOf(picture))

    override fun updateUserProfilePicture(picture: MultipartFile, userId: Long) =
        updatePictures(USER, userId, setOf(picture))

    override fun saveDestinationsPictures(pictures: Set<MultipartFile>, destinationId: Long) =
        savePictures(DESTINATION, destinationId, pictures)

    override fun updateDestinationsPictures(pictures: Set<MultipartFile>, destinationId: Long) =
        updatePictures(DESTINATION, destinationId, pictures)

    override fun getUserProfilePicture(userId: Long): PictureResponseDTO? =
        getPictures(USER, userId)?.toDTO(getBucket(USER))

    override fun getDestinationsPictures(destinationId: Long): PictureResponseDTO? =
        getPictures(DESTINATION, destinationId)?.toDTO(getBucket(DESTINATION))

    override fun removeUserProfilePicture(userId: Long) {
        removePictures(USER, userId)
    }

    override fun removeDestinationsPictures(destinationId: Long) {
        removePictures(DESTINATION, destinationId)
    }

    private fun getPictures(type: PictureTypeEnum, id: Long) =
        amazonDynamoDBRepository.select(id, type.name)

    private fun removePictures(type: PictureTypeEnum, id: Long) = amazonDynamoDBRepository.delete(id, type.name)

    private fun savePictures(type: PictureTypeEnum, id: Long, pictures: Set<MultipartFile>): PictureResponseDTO {
        val picturesToSave = mutableSetOf<String>()
        val bucket = getBucket(type)
        pictures.forEachIndexed { index, file ->
            val fileNameInBucket = formatFileNameInBucket(id, type, index)
            amazonS3Repository.createPicture(fileNameInBucket, bucket, file, type)
            picturesToSave.add(fileNameInBucket)
        }

        val s3ObjectStorageEntity = S3ObjectStorageEntity(
            id = id,
            type = type.name,
            files = picturesToSave
        )
        amazonDynamoDBRepository.insert(
            s3ObjectStorageEntity
        )

        return s3ObjectStorageEntity.toDTO(bucket)
    }

    private fun updatePictures(type: PictureTypeEnum, id: Long, pictures: Set<MultipartFile>): PictureResponseDTO {
        val picturesToSave = mutableSetOf<String>()
        val bucket = getBucket(type)
        pictures.forEachIndexed { index, file ->
            val fileNameInBucket = formatFileNameInBucket(id, type, index)
            amazonS3Repository.updatePicture(fileNameInBucket, bucket, file, type)
            picturesToSave.add(fileNameInBucket)
        }

        val s3ObjectStorageEntity = S3ObjectStorageEntity(
            id = id,
            type = type.name,
            files = picturesToSave
        )
        amazonDynamoDBRepository.update(
            s3ObjectStorageEntity
        )

        return s3ObjectStorageEntity.toDTO(bucket)
    }

    private fun getBucket(type: PictureTypeEnum) = when (type) {
        USER -> picturesVars.usersBucketName
        DESTINATION -> picturesVars.destinationsBucketName
    }

    private fun formatFileNameInBucket(id: Long, type: PictureTypeEnum, index: Int) = "${type.name}_${id}_$index"

}