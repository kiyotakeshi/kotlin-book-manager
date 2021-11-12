package com.kiyotakeshi.bookmanage.application.service

import com.kiyotakeshi.bookmanage.domain.model.User
import com.kiyotakeshi.bookmanage.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class AuthenticationService(private val userRepository: UserRepository) {
    fun findUser(email: String): User? {
        return userRepository.find(email)
    }
}
