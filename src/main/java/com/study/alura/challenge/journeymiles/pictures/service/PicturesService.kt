package com.study.alura.challenge.journeymiles.pictures.service

import com.study.alura.challenge.journeymiles.pictures.dto.response.PictureResponseDTO
import org.springframework.web.multipart.MultipartFile

interface PicturesService {

    fun saveUserProfilePicture(picture: MultipartFile, userId: Long): PictureResponseDTO

    fun updateUserProfilePicture(picture: MultipartFile, userId: Long): PictureResponseDTO

    fun saveDestinationsPictures(pictures: Set<MultipartFile>, destinationId: Long): PictureResponseDTO

    fun updateDestinationsPictures(pictures: Set<MultipartFile>, destinationId: Long): PictureResponseDTO

    fun removeUserProfilePicture(userId: Long)

    fun removeDestinationsPictures(destinationId: Long)

    fun getUserProfilePicture(userId: Long): PictureResponseDTO?

    fun getDestinationsPictures(destinationId: Long): PictureResponseDTO?
}