package com.study.alura.challenge.journeymiles.destinations.service

import com.study.alura.challenge.journeymiles.destinations.dto.request.CreateOrUpdateDestinationRequestDTO
import com.study.alura.challenge.journeymiles.destinations.dto.response.DestinationsResponseDTO
import com.study.alura.challenge.journeymiles.destinations.dto.response.SearchDestinationsResponseDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.multipart.MultipartFile

interface DestinationsService {

    fun createDestination(
        pictures: Set<MultipartFile>?,
        createOrUpdateDestinationRequestDTO: CreateOrUpdateDestinationRequestDTO
    ): DestinationsResponseDTO

    fun updateDestination(
        createOrUpdateDestinationRequestDTO: CreateOrUpdateDestinationRequestDTO,
        pictures: Set<MultipartFile>?,
        id: Long
    ): DestinationsResponseDTO

    fun deleteDestination(id: Long): Boolean

    fun searchDestinationById(id: Long): DestinationsResponseDTO

    fun getDestinations(pageable: Pageable): Page<DestinationsResponseDTO>

    fun searchDestinationByName(name: String, pageable: Pageable): Page<SearchDestinationsResponseDTO>

    fun verifyIfDestinationExists(destinationId: Long): Boolean
}