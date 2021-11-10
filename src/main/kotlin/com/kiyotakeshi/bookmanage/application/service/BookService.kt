package com.kiyotakeshi.bookmanage.application.service

import com.kiyotakeshi.bookmanage.domain.model.BookWithRental
import com.kiyotakeshi.bookmanage.domain.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(
    private val bookRepository: BookRepository
) {
    fun getList(): List<BookWithRental> {
        return bookRepository.findAllWithRental()
    }
}