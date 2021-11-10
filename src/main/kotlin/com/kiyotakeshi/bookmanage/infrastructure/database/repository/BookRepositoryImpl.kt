package com.kiyotakeshi.bookmanage.infrastructure.database.repository

import com.kiyotakeshi.bookmanage.domain.model.Book
import com.kiyotakeshi.bookmanage.domain.model.BookWithRental
import com.kiyotakeshi.bookmanage.domain.model.Rental
import com.kiyotakeshi.bookmanage.domain.repository.BookRepository
import com.kiyotakeshi.bookmanage.infrastructure.database.mapper.custom.BookWithRentalMapper
import com.kiyotakeshi.bookmanage.infrastructure.database.mapper.custom.select
import com.kiyotakeshi.bookmanage.infrastructure.database.record.custom.BookWithRentalRecord
import org.springframework.stereotype.Repository

@Suppress("SpringJavaInjectionPointsAutowiringInspection")
@Repository
class BookRepositoryImpl(
    private val bookWithRentalMapper: BookWithRentalMapper
) : BookRepository {
    override fun findAllWithRental(): List<BookWithRental> {
        return bookWithRentalMapper.select().map { toModel(it) }
    }

    // BookWithRentalRecord は MyBatis の仕様に依存したクラスのため変換する
    private fun toModel(record: BookWithRentalRecord): BookWithRental {
        val book = Book(
            record.id!!,
            record.title!!,
            record.author!!,
            record.releaseDate!!
        )
        val rental = record.userId?.let {
            Rental(
                record.id!!,
                record.userId!!,
                record.rentalDatetime!!,
                record.returnDeadline!!,
            )
        }
        return BookWithRental(book, rental)
    }
}