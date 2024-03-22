package com.study.alura.challenge.journeymiles.destinations.service.impl

import com.study.alura.challenge.journeymiles.destinations.dto.request.CreateOrUpdateDestinationRequestDTO
import com.study.alura.challenge.journeymiles.destinations.dto.response.DestinationsResponseDTO
import com.study.alura.challenge.journeymiles.destinations.dto.response.SearchDestinationsResponseDTO
import com.study.alura.challenge.journeymiles.destinations.mappers.toEntity
import com.study.alura.challenge.journeymiles.destinations.mappers.toResponse
import com.study.alura.challenge.journeymiles.destinations.mappers.toSearchResponseDTO
import com.study.alura.challenge.journeymiles.destinations.repository.DestinationsRepository
import com.study.alura.challenge.journeymiles.destinations.service.DestinationsService
import com.study.alura.challenge.journeymiles.model.exceptions.DestinationNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class DestinationsServiceImpl(
    private val destinationsRepository: DestinationsRepository
) : DestinationsService {
    override fun createDestination(createOrUpdateDestinationRequestDTO: CreateOrUpdateDestinationRequestDTO): DestinationsResponseDTO {
        return destinationsRepository.save(createOrUpdateDestinationRequestDTO.toEntity()).toResponse()
    }

    override fun updateDestination(
        createOrUpdateDestinationRequestDTO: CreateOrUpdateDestinationRequestDTO,
        id: Long
    ): DestinationsResponseDTO {
        destinationsRepository.findByIdOrNull(id)?.let { destination ->
            val updatedDestination = destination.copy(
                price = createOrUpdateDestinationRequestDTO.price,
                name = createOrUpdateDestinationRequestDTO.name,
                description = createOrUpdateDestinationRequestDTO.description,
                meta = createOrUpdateDestinationRequestDTO.meta
            )
            return destinationsRepository.save(updatedDestination).toResponse()
        } ?: throw DestinationNotFoundException(id)
    }

    override fun deleteDestination(id: Long): Boolean {
        destinationsRepository.findByIdOrNull(id)?.let { destination ->
            destinationsRepository.delete(destination)
            return true
        } ?: throw DestinationNotFoundException(id)
    }

    override fun searchDestinationById(id: Long): DestinationsResponseDTO {
        return destinationsRepository.findByIdOrNull(id)?.toResponse() ?: throw DestinationNotFoundException(id)
    }

    override fun getDestinations(pageable: Pageable): Page<DestinationsResponseDTO> {
        return destinationsRepository.findAll(pageable).map { it.toResponse() }
    }

    override fun searchDestinationByName(name: String, pageable: Pageable): Page<SearchDestinationsResponseDTO> {
        return destinationsRepository.searchByName(name, pageable).map {
            it.toSearchResponseDTO()
        }
    }
}
