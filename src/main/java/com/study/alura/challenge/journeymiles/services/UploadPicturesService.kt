package com.study.alura.challenge.journeymiles.services

import com.study.alura.challenge.journeymiles.model.enums.PictureTypeEnum
import org.springframework.web.multipart.MultipartFile

interface UploadPicturesService {

    fun uploadPicture(pictureTypeEnum: PictureTypeEnum, id: Long, file: MultipartFile)

}