package com.study.alura.challenge.journeymiles.pictures.service.impl

import com.study.alura.challenge.journeymiles.config.vars.PictureVars
import com.study.alura.challenge.journeymiles.model.enums.PictureTypeEnum
import com.study.alura.challenge.journeymiles.pictures.dto.response.PictureResponseDTO
import com.study.alura.challenge.journeymiles.pictures.mappers.toDTO
import com.study.alura.challenge.journeymiles.pictures.repository.AmazonDynamoDBRepository
import com.study.alura.challenge.journeymiles.pictures.service.GetPicturesService
import org.springframework.stereotype.Service

@Service
class GetPicturesServiceImpl(
    private val amazonDynamoDBRepository: AmazonDynamoDBRepository,
    private val picturesVars: PictureVars,
) : GetPicturesService {

    override fun getUserProfilePicture(userId: Long): PictureResponseDTO? {
        return getPictures(PictureTypeEnum.USER, userId)?.toDTO(getBucket(PictureTypeEnum.USER))
    }

    override fun getDestinationsPictures(destinationId: Long): PictureResponseDTO? {
        return getPictures(PictureTypeEnum.DESTINATION, destinationId)?.toDTO(getBucket(PictureTypeEnum.DESTINATION))
    }

    private fun getPictures(type: PictureTypeEnum, id: Long) =
        amazonDynamoDBRepository.select(id, type.name)

    private fun getBucket(type: PictureTypeEnum) = when (type) {
        PictureTypeEnum.USER -> picturesVars.usersBucketName
        PictureTypeEnum.DESTINATION -> picturesVars.destinationsBucketName
    }
}