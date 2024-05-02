package com.study.alura.challenge.journeymiles.security.dto

import com.study.alura.challenge.journeymiles.model.entity.UserEntity
import com.study.alura.challenge.journeymiles.model.enums.RolesEnum
import org.springframework.security.core.userdetails.UserDetails

class UserDetail(private val user: UserEntity) : UserDetails {
    override fun getAuthorities() = listOf(RolesEnum.CUSTOMER)

    override fun getPassword() = user.password

    override fun getUsername() = user.email

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}