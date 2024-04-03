package com.study.alura.challenge.journeymiles.pictures.dto.response

import com.study.alura.challenge.journeymiles.model.enums.PictureTypeEnum

data class PictureResponseDTO(
    val id: Long,
    val type: PictureTypeEnum,
    val bucket: String,
    val pictures: Set<String>
)
