package com.study.alura.challenge.journeymiles.pictures.repository

import com.study.alura.challenge.journeymiles.model.enums.PictureTypeEnum
import com.study.alura.challenge.journeymiles.utils.AmazonS3Utils
import com.study.alura.challenge.journeymiles.utils.FileUtils
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Repository
import org.springframework.web.multipart.MultipartFile

@Repository
class AmazonS3Repository(
    private val amazonS3: AmazonS3Utils, private val fileUtils: FileUtils
) {

    private val logger = LogManager.getLogger(this::class.java)

    fun createPicture(fileName: String, bucket: String, file: MultipartFile, type: PictureTypeEnum) {
        logger.info("create new ${type.name} picture with name: $fileName")
        amazonS3.createFile(bucket, fileName, fileUtils.convertMultipartToFile(file))
    }

    fun updatePicture(fileName: String, bucket: String, file: MultipartFile, type: PictureTypeEnum) {
        logger.info("update ${type.name} picture with name: $file")
        amazonS3.updateFile(bucket, fileName, fileUtils.convertMultipartToFile(file))
    }

    fun deletePicture(fileName: String, bucket: String, type: PictureTypeEnum) {
        logger.info("Removing a picture for ${type.name} with name: $fileName")
        amazonS3.deleteFile(bucket, fileName)
    }

    fun doesPictureExist(fileName: String, bucket: String) = amazonS3.doesObjectExist(bucket, fileName)

}