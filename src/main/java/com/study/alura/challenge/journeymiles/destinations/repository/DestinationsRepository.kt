package com.study.alura.challenge.journeymiles.destinations.repository

import com.study.alura.challenge.journeymiles.model.entity.DestinationEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface DestinationsRepository : JpaRepository<DestinationEntity, Long> {

    @Query("SELECT * from destinations WHERE LOWER(name) LIKE %:name% ORDER BY name", nativeQuery = true)
    fun searchByName(name: String, pageable: Pageable): Page<DestinationEntity>

}