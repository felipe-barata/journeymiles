package com.study.alura.challenge.journeymiles.destinations.service.impl

import com.study.alura.challenge.journeymiles.destinations.dto.request.CreateOrUpdateDestinationRequestDTO
import com.study.alura.challenge.journeymiles.destinations.dto.response.DestinationsResponseDTO
import com.study.alura.challenge.journeymiles.destinations.dto.response.SearchDestinationsResponseDTO
import com.study.alura.challenge.journeymiles.destinations.mappers.toEntity
import com.study.alura.challenge.journeymiles.destinations.mappers.toResponse
import com.study.alura.challenge.journeymiles.destinations.mappers.toSearchResponseDTO
import com.study.alura.challenge.journeymiles.destinations.repository.DestinationsRepository
import com.study.alura.challenge.journeymiles.destinations.service.DestinationsService
import com.study.alura.challenge.journeymiles.model.entity.DestinationsEntity
import com.study.alura.challenge.journeymiles.model.exceptions.DestinationNotFoundException
import com.study.alura.challenge.journeymiles.pictures.service.impl.GetPicturesServiceImpl
import java.time.OffsetDateTime
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class DestinationsServiceImpl(
    private val picturesService: GetPicturesServiceImpl,
    private val destinationsRepository: DestinationsRepository
) : DestinationsService {
    override fun createDestination(createOrUpdateDestinationRequestDTO: CreateOrUpdateDestinationRequestDTO): DestinationsResponseDTO {
        return destinationsRepository.save(createOrUpdateDestinationRequestDTO.toEntity()).toResponse()
    }

    override fun updateDestination(
        createOrUpdateDestinationRequestDTO: CreateOrUpdateDestinationRequestDTO,
        id: Long
    ): DestinationsResponseDTO {
        val destination = getDestinationFromDatabase(id)
        val updatedDestination = destination.copy(
            price = createOrUpdateDestinationRequestDTO.price,
            name = createOrUpdateDestinationRequestDTO.name,
            description = createOrUpdateDestinationRequestDTO.description,
            meta = createOrUpdateDestinationRequestDTO.meta,
            updatedAt = OffsetDateTime.now()
        )
        return destinationsRepository.save(updatedDestination).toResponse()
    }

    override fun deleteDestination(id: Long): Boolean {
        destinationsRepository.delete(getDestinationFromDatabase(id))
        return true
    }

    override fun searchDestinationById(id: Long): DestinationsResponseDTO {
        val destination = getDestinationFromDatabase(id)
        return destinationToResponse(destination)
    }

    override fun getDestinations(pageable: Pageable): Page<DestinationsResponseDTO> {
        return destinationsRepository.findAll(pageable).map {
            destinationToResponse(it)
        }
    }

    override fun searchDestinationByName(name: String, pageable: Pageable): Page<SearchDestinationsResponseDTO> {
        return destinationsRepository.searchByName(name, pageable).map {
            destinationToSearchResponse(it)
        }
    }

    override fun verifyIfDestinationExists(destinationId: Long): Boolean {
        return runCatching { getDestinationFromDatabase(destinationId) }.onFailure { throw it }.isSuccess
    }

    private fun getDestinationFromDatabase(destinationId: Long) =
        destinationsRepository.findByIdOrNull(destinationId) ?: throw DestinationNotFoundException(destinationId)

    private fun destinationToResponse(destinationsEntity: DestinationsEntity) = destinationsEntity.toResponse(
        picturesService.getDestinationsPictures(destinationsEntity.id!!)?.pictures ?: emptySet()
    )

    private fun destinationToSearchResponse(destinationsEntity: DestinationsEntity) =
        destinationsEntity.toSearchResponseDTO(
            picturesService.getDestinationsPictures(destinationsEntity.id!!)?.pictures ?: emptySet()
        )
}
