package com.study.alura.challenge.journeymiles.security.dto

import com.study.alura.challenge.journeymiles.model.enums.RolesEnum
import org.springframework.security.core.userdetails.UserDetails

class ServiceUserDetail(
    private val sharedSecret: String
) : UserDetails {
    override fun getAuthorities() = listOf(RolesEnum.SERVICE)

    override fun getPassword() = sharedSecret

    override fun getUsername() = "SERVICE"

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}