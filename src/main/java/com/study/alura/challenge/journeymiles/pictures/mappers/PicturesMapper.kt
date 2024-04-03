package com.study.alura.challenge.journeymiles.pictures.mappers

import com.study.alura.challenge.journeymiles.model.entity.S3ObjectStorageEntity
import com.study.alura.challenge.journeymiles.model.enums.PictureTypeEnum
import com.study.alura.challenge.journeymiles.pictures.dto.response.PictureResponseDTO

fun S3ObjectStorageEntity.toDTO(bucket: String) = PictureResponseDTO(
    id = this.id,
    type = PictureTypeEnum.getType(this.type),
    bucket = bucket,
    pictures = this.files
)