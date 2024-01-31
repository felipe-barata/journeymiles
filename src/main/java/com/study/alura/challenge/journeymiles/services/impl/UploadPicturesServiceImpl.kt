package com.study.alura.challenge.journeymiles.services.impl

import com.study.alura.challenge.journeymiles.model.enums.PictureTypeEnum
import com.study.alura.challenge.journeymiles.repository.TestimonialsRepository
import com.study.alura.challenge.journeymiles.services.UploadPicturesService
import org.springframework.web.multipart.MultipartFile

class UploadPicturesServiceImpl(
    private val testimonialsRepository: TestimonialsRepository
): UploadPicturesService {
    override fun uploadPicture(pictureTypeEnum: PictureTypeEnum, id: Long, file: MultipartFile) {
        TODO("Not yet implemented")
    }
}