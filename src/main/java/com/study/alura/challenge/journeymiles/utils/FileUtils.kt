package com.study.alura.challenge.journeymiles.utils

import java.io.File
import java.io.FileOutputStream
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class FileUtils {

    fun convertMultipartToFile(file: MultipartFile): File {
        val newFile = File(file.originalFilename!!)
        val fileOutputStream = FileOutputStream(newFile)
        fileOutputStream.write(file.bytes)
        fileOutputStream.close()
        return newFile
    }

    fun convertBytesToFile(bytes: ByteArray, fileName: String): File {
        val file = File(fileName)
        val outputStream = FileOutputStream(file)
        outputStream.write(bytes)
        outputStream.close()
        return file
    }

}