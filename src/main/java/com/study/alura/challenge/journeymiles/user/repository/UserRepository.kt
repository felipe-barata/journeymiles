package com.study.alura.challenge.journeymiles.user.repository

import com.study.alura.challenge.journeymiles.model.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserEntity, Long> {
}