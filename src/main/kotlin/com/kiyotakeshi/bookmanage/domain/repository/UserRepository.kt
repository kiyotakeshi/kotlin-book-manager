package com.kiyotakeshi.bookmanage.domain.repository

import com.kiyotakeshi.bookmanage.domain.model.User

interface UserRepository {
    fun find(email: String): User?
}
