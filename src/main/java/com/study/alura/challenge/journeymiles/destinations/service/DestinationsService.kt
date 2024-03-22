package com.study.alura.challenge.journeymiles.destinations.service

import com.study.alura.challenge.journeymiles.destinations.dto.request.CreateOrUpdateDestinationRequestDTO
import com.study.alura.challenge.journeymiles.destinations.dto.response.DestinationsResponseDTO
import com.study.alura.challenge.journeymiles.destinations.dto.response.SearchDestinationsResponseDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface DestinationsService {

    fun createDestination(createOrUpdateDestinationRequestDTO: CreateOrUpdateDestinationRequestDTO): DestinationsResponseDTO

    fun updateDestination(
        createOrUpdateDestinationRequestDTO: CreateOrUpdateDestinationRequestDTO,
        id: Long
    ): DestinationsResponseDTO

    fun deleteDestination(id: Long): Boolean

    fun searchDestinationById(id: Long): DestinationsResponseDTO

    fun getDestinations(pageable: Pageable): Page<DestinationsResponseDTO>

    fun searchDestinationByName(name: String, pageable: Pageable): Page<SearchDestinationsResponseDTO>
}