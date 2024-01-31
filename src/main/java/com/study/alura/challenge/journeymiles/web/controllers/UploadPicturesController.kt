package com.study.alura.challenge.journeymiles.web.controllers

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/upload")
class UploadPicturesController {

    @PostMapping("/testimonials/{id}")
    fun uploadTestimonialPicture(@PathVariable id: Long, @RequestPart(value = "file") file: MultipartFile) {

    }

}