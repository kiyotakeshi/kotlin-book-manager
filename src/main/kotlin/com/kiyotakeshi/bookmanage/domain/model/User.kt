package com.kiyotakeshi.bookmanage.domain.model

import com.kiyotakeshi.bookmanage.domain.enum.RoleType

data class User(
    val id: Long,
    val email: String,
    val password: String,
    val name: String,
    val roleType: RoleType
)