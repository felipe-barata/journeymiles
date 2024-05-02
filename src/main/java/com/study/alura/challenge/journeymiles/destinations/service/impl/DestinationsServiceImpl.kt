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
import com.study.alura.challenge.journeymiles.pictures.service.PicturesService
import java.time.OffsetDateTime
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class DestinationsServiceImpl(
    private val picturesService: PicturesService,
    private val destinationsRepository: DestinationsRepository
) : DestinationsService {
    override fun createDestination(
        pictures: Set<MultipartFile>?,
        createOrUpdateDestinationRequestDTO: CreateOrUpdateDestinationRequestDTO
    ): DestinationsResponseDTO {
        val savedDestination = destinationsRepository.save(createOrUpdateDestinationRequestDTO.toEntity())
        takeIf { !pictures.isNullOrEmpty() }?.run {
            picturesService.saveDestinationsPictures(
                pictures!!,
                savedDestination.id!!
            )
        }
        return savedDestination.toResponse()
    }

    override fun updateDestination(
        createOrUpdateDestinationRequestDTO: CreateOrUpdateDestinationRequestDTO,
        pictures: Set<MultipartFile>?,
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
        takeIf { !pictures.isNullOrEmpty() }?.run {
            picturesService.updateDestinationsPictures(pictures!!, id)
        }
        return destinationsRepository.save(updatedDestination).toResponse()
    }

    override fun deleteDestination(id: Long): Boolean {
        destinationsRepository.delete(getDestinationFromDatabase(id))
        picturesService.removeDestinationsPictures(id)
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
        getDestinationPictures(destinationsEntity.id!!)
    )

    private fun destinationToSearchResponse(destinationsEntity: DestinationsEntity) =
        destinationsEntity.toSearchResponseDTO(
            getDestinationPictures(destinationsEntity.id!!)
        )

    private fun getDestinationPictures(destinationId: Long) =
        picturesService.getDestinationsPictures(destinationId)?.pictures ?: emptySet()
}
