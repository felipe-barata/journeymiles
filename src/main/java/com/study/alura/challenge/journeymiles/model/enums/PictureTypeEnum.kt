package com.study.alura.challenge.journeymiles.model.enums

import com.study.alura.challenge.journeymiles.model.exceptions.UnsupportedPictureTypeException

enum class PictureTypeEnum {
    USER,
    DESTINATION;

    companion object {

        fun getType(type: String) = runCatching {
            valueOf(type)
        }.onFailure {
            throw UnsupportedPictureTypeException(type)
        }.getOrThrow()
    }

}