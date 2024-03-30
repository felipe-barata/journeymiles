package com.study.alura.challenge.journeymiles.pictures.controller

import com.study.alura.challenge.journeymiles.pictures.dto.response.PictureResponseDTO
import com.study.alura.challenge.journeymiles.pictures.service.PicturesService
import org.apache.logging.log4j.LogManager
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/pictures")
@Validated
class PicturesController(private val picturesService: PicturesService) {

    private val logger = LogManager.getLogger(this::class.java)

    @PostMapping("/users/{id}")
    fun saveUserProfilePicture(
        @RequestParam(value = "picture") picture: MultipartFile,
        @PathVariable(value = "id") userId: Long
    ): ResponseEntity<PictureResponseDTO> =
        ResponseEntity.status(HttpStatus.CREATED).body(picturesService.saveUserProfilePicture(picture, userId))

    @PutMapping("/users/{id}")
    fun updateUserProfilePicture(
        @RequestParam(value = "pictures") picture: MultipartFile,
        @PathVariable(value = "id") userId: Long
    ) =
        ResponseEntity.ok(picturesService.updateUserProfilePicture(picture, userId))

    @PostMapping("/destinations/{id}")
    fun saveDestinationsPictures(
        @RequestParam(value = "picture") pictures: Set<MultipartFile>,
        @PathVariable(value = "id") destinationId: Long
    ) =
        ResponseEntity.status(HttpStatus.CREATED)
            .body(picturesService.saveDestinationsPictures(pictures, destinationId))

    @PutMapping("/destinations/{id}")
    fun updateDestinationsPictures(
        @RequestParam(value = "pictures") pictures: Set<MultipartFile>,
        @PathVariable(value = "id") destinationId: Long
    ) =
        ResponseEntity.ok(picturesService.updateDestinationsPictures(pictures, destinationId))

    @GetMapping("/users/{id}")
    fun getUserProfilePicture(@PathVariable(value = "id") userId: Long) =
        ResponseEntity.ok(picturesService.getUserProfilePicture(userId)) ?: ResponseEntity.noContent().build()

    @GetMapping("/destinations/{id}")
    fun getDestinationsPictures(@PathVariable(value = "id") destinationId: Long) =
        ResponseEntity.ok(picturesService.getDestinationsPictures(destinationId)) ?: ResponseEntity.noContent().build()

    @DeleteMapping("/users/{id}")
    fun removeUserProfilePicture(@PathVariable(value = "id") userId: Long): ResponseEntity<Void> =
        picturesService.removeUserProfilePicture(userId).let {
            ResponseEntity.noContent().build()
        }

    @DeleteMapping("/destinations/{id}")
    fun removeDestinationsPictures(@PathVariable(value = "id") destinationId: Long): ResponseEntity<Void> =
        picturesService.removeDestinationsPictures(destinationId).let {
            ResponseEntity.noContent().build()
        }

}