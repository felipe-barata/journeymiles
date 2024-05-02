package com.study.alura.challenge.journeymiles.model.enums

import org.springframework.security.core.GrantedAuthority

enum class RolesEnum : GrantedAuthority {
    SERVICE {
        override fun getAuthority() = "ROLE_SERVICE"
    },
    CUSTOMER {
        override fun getAuthority() = "ROLE_CUSTOMER"
    }
}