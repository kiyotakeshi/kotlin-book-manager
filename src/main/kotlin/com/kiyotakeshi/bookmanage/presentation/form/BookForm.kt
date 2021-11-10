package com.kiyotakeshi.bookmanage.presentation.form

import com.kiyotakeshi.bookmanage.domain.model.BookWithRental

data class GetBookListResponse(val books: List<BookInfo>)

// レスポンスのオブジェクトなのでドメインオブジェクトと別のクラスとして定義
data class BookInfo(
    val id: Long,
    val title: String,
    val author: String,
    // 画面表示に必要な真偽値
    val isRental: Boolean
) {
    constructor(model: BookWithRental) : this(model.book.id, model.book.title, model.book.author, model.isRental)
}
