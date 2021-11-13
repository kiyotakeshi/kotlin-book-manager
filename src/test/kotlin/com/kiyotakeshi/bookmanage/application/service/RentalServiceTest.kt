package com.kiyotakeshi.bookmanage.application.service

import com.kiyotakeshi.bookmanage.domain.enum.RoleType
import com.kiyotakeshi.bookmanage.domain.model.Book
import com.kiyotakeshi.bookmanage.domain.model.BookWithRental
import com.kiyotakeshi.bookmanage.domain.model.Rental
import com.kiyotakeshi.bookmanage.domain.model.User
import com.kiyotakeshi.bookmanage.domain.repository.BookRepository
import com.kiyotakeshi.bookmanage.domain.repository.RentalRepository
import com.kiyotakeshi.bookmanage.domain.repository.UserRepository
import com.nhaarman.mockitokotlin2.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalStateException
import java.time.LocalDate
import java.time.LocalDateTime

internal class RentalServiceTest {
    private val userRepository = mock<UserRepository>()
    private val bookRepository = mock<BookRepository>()
    private val rentalRepository = mock<RentalRepository>()

    private val rentalService = RentalService(userRepository, bookRepository, rentalRepository)

    @Test
    fun `endRental when book is rental then delete to rental`() {
        val userId = 100L
        val bookId = 1000L
        val user = User(userId, "test@test.com", "pass", "kotlin", RoleType.USER)
        val book = Book(bookId, "kotlin beginner", "mike", LocalDate.now())
        val rental = Rental(bookId, userId, LocalDateTime.now(), LocalDateTime.MAX)
        val bookWithRental = BookWithRental(book, rental)

        whenever(userRepository.find(any() as Long)).thenReturn(user)
        // 引数によって戻り値を変える場合
        // whenever(userRepository.find(1L)).thenReturn(user1)
        whenever(bookRepository.findWithRental(any() as Long)).thenReturn(bookWithRental)

        rentalService.endRental(bookId, userId)

        // mock 化した関数が想定通りの引数を渡して実行されているかを検証
        verify(userRepository).find(userId)
        // 呼び出されていないことを確認する場合(分岐パターンにより実行されないケースに使用)
        // verify(userRepository, times(0)).find(userId)
        verify(bookRepository).findWithRental(bookId)
        verify(rentalRepository).endRental(bookId)
    }

    @Test
    fun `endRental when book is not rental then throw exception`() {
        val userId = 100L
        val bookId = 1000L
        val user = User(userId, "test@test.com", "pass", "kotlin", RoleType.USER)
        val book = Book(bookId, "kotlin beginner", "mike", LocalDate.now())
        val bookWithRental = BookWithRental(book, null)

        whenever(userRepository.find(any() as Long)).thenReturn(user)
        whenever(bookRepository.findWithRental(any() as Long)).thenReturn(bookWithRental)

        val exception = assertThrows(IllegalStateException::class.java) {
            rentalService.endRental(bookId, userId)
        }

        assertThat(exception.message).isEqualTo("this book is available(not rented) . bookId: $bookId")

        verify(userRepository).find(userId)
        verify(bookRepository).findWithRental(bookId)
        // 呼び出されていないことを確認
        verify(rentalRepository, times(0)).endRental(any())
    }
}