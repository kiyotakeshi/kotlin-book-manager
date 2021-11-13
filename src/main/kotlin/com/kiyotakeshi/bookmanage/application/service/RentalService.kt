package com.kiyotakeshi.bookmanage.application.service

import com.kiyotakeshi.bookmanage.domain.model.Rental
import com.kiyotakeshi.bookmanage.domain.repository.BookRepository
import com.kiyotakeshi.bookmanage.domain.repository.RentalRepository
import com.kiyotakeshi.bookmanage.domain.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

private const val RENTAL_TERM_DAYS = 14L

@Service
class RentalService(
    private val userRepository: UserRepository,
    private val bookRepository: BookRepository,
    private val rentalRepository: RentalRepository
) {
    @Transactional
    fun startRental(bookId: Long, userId: Long) {
        userRepository.find(userId) ?: throw IllegalArgumentException("not exist userId: $userId")

        val book =
            bookRepository.findWithRental(bookId) ?: throw IllegalArgumentException("not exist bookId: $bookId")

        if (book.isRental) throw IllegalStateException("this book already rent. bookId: $bookId")

        val rentalDateTime = LocalDateTime.now()
        val returnDeadLine = rentalDateTime.plusDays(RENTAL_TERM_DAYS)
        val rental = Rental(bookId, userId, rentalDateTime, returnDeadLine)

        rentalRepository.startRental(rental)
    }

    @Transactional
    fun endRental(bookId: Long, userId: Long){
        userRepository.find(userId) ?: throw IllegalArgumentException("not exist userId: $userId")

        val book =
            bookRepository.findWithRental(bookId) ?: throw IllegalArgumentException("not exist bookId: $bookId")

        if (!book.isRental) throw IllegalStateException("this book is available(not rented) . bookId: $bookId")
        if (book.rental?.userId != userId) throw IllegalStateException("this book already rent other user. userId: $userId bookId: $bookId")

        rentalRepository.endRental(bookId)
    }
}