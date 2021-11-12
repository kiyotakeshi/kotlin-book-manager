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

    fun getDetail(bookId: Long): BookWithRental {
        // null だった場合に例外を投げるようエルビス演算子を使用
        return bookRepository.findWithRental(bookId) ?: throw IllegalArgumentException("not exist id: $bookId")
    }
}