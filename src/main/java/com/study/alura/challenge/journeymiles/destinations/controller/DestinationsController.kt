package com.study.alura.challenge.journeymiles.destinations.controller

import com.study.alura.challenge.journeymiles.destinations.dto.request.CreateOrUpdateDestinationRequestDTO
import com.study.alura.challenge.journeymiles.destinations.dto.response.DestinationsResponseDTO
import com.study.alura.challenge.journeymiles.destinations.dto.response.SearchDestinationsResponseDTO
import com.study.alura.challenge.journeymiles.destinations.service.DestinationsService
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.apache.logging.log4j.LogManager
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/destinations")
@Validated
class DestinationsController(private val destinationsService: DestinationsService) {

    private val logger = LogManager.getLogger(this::class.java)

    @PostMapping
    fun create(
        @Valid @RequestBody createOrUpdateDestinationRequestDTO: CreateOrUpdateDestinationRequestDTO
    ): ResponseEntity<DestinationsResponseDTO> {
        return ResponseEntity.status(HttpStatus.CREATED).body(
            destinationsService.createDestination(createOrUpdateDestinationRequestDTO).also { destinationsResponse ->
                logger.info("Created a new testimonial with id ${destinationsResponse.id} and name: ${destinationsResponse.name}")
            })
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody createOrUpdateDestinationRequestDTO: CreateOrUpdateDestinationRequestDTO
    ): ResponseEntity<DestinationsResponseDTO> {
        return ResponseEntity.ok(
            destinationsService.updateDestination(createOrUpdateDestinationRequestDTO, id)
                .also { destinationsResponse ->
                    logger.info("Updated a testimonial with id ${destinationsResponse.id} and name: ${destinationsResponse.name}")
                })
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> = destinationsService.deleteDestination(id).let {
        ResponseEntity.noContent().build()
    }

    @GetMapping
    fun searchDestinationByName(
        @RequestParam @Size(min = 3) @NotBlank query: String,
        pageable: Pageable
    ): ResponseEntity<Page<SearchDestinationsResponseDTO>> {
        return ResponseEntity.ok(destinationsService.searchDestinationByName(query.lowercase(), pageable))
    }

    @GetMapping("/all")
    fun getDestinations(
        pageable: Pageable
    ): ResponseEntity<Page<DestinationsResponseDTO>> {
        return ResponseEntity.ok(destinationsService.getDestinations(pageable))
    }

    @GetMapping("/{id}")
    fun searchDestinationById(@PathVariable id: Long): ResponseEntity<DestinationsResponseDTO> =
        ResponseEntity.ok(destinationsService.searchDestinationById(id))
}
