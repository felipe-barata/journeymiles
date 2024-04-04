package com.study.alura.challenge.journeymiles.pictures.service

import com.study.alura.challenge.journeymiles.pictures.dto.response.PictureResponseDTO

interface GetPicturesService {

    fun getUserProfilePicture(userId: Long): PictureResponseDTO?

    fun getDestinationsPictures(destinationId: Long): PictureResponseDTO?

}