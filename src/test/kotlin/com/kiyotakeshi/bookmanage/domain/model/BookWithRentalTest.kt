// テスト対象とパッケージ構成を揃えておくことで対象のクラスを import する必要がない
package com.kiyotakeshi.bookmanage.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime

internal class BookWithRentalTest {

    @Test
    @DisplayName("拡張プロパティのテスト")
    fun `isRental When rental is null then return false`() {
        val book = Book(1, "kotlin beginner", "mike", LocalDate.now())
        val bookWithRental = BookWithRental(book, null)
        assertThat(bookWithRental.isRental).isEqualTo(false)
    }

    @Test
    @DisplayName("拡張プロパティのテスト2")
    fun `isRental when rental is not null then return true`() {
        val book = Book(1, "kotlin beginner", "mike", LocalDate.now())
        val rental = Rental(1, 100, LocalDateTime.now(), LocalDateTime.MAX)
        val bookWithRental = BookWithRental(book, rental)
        assertThat(bookWithRental.isRental).isEqualTo(true)
    }
}