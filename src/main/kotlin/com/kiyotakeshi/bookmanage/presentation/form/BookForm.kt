package com.kiyotakeshi.bookmanage.presentation.form

import com.kiyotakeshi.bookmanage.domain.model.BookWithRental
import com.kiyotakeshi.bookmanage.domain.model.Rental
import java.time.LocalDate
import java.time.LocalDateTime

data class GetBookListResponse(val books: List<BookInfo>)

// レスポンスのオブジェクトなのでドメインオブジェクトと別のクラスとして定義
data class BookInfo(
    val id: Long,
    val title: String,
    val author: String,
    // 画面表示に必要な真偽値
    val isRental: Boolean
) {
    constructor(model: BookWithRental) : this(
        model.book.id,
        model.book.title,
        model.book.author,
        model.isRental
    )
}

data class GetBookDetailResponse(
    val id: Long,
    val title: String,
    val author: String,
    val releaseDate: LocalDate,
    val rentalInfo: RentalInfo?
) {
    constructor(model: BookWithRental) : this(
        model.book.id,
        model.book.title,
        model.book.author,
        model.book.releaseDate,
        model.rental?.let { RentalInfo(model.rental) }
    )
}

data class RentalInfo(
    val userId: Long,
    val rentalDateTime: LocalDateTime,
    val returnDeadLine: LocalDateTime,
) {
    constructor(rental: Rental) : this(rental.userId, rental.rentalDatetime, rental.returnDeadline)
}

data class RegisterBookRequest(
    val id: Long,
    val title: String,
    val author: String,
    val releaseDate: LocalDate
)

data class UpdateBookRequest(
    val title: String?,
    val author: String?,
    val releaseDate: LocalDate?
)
