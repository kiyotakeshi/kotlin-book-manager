package com.kiyotakeshi.bookmanage.domain.model

data class BookWithRental(
    val book: Book,
    // null 許容(貸し出されていない場合は null)
    val rental: Rental?
) {
    // 拡張プロパティ
    // rental の値をチェックし、貸し出し中か boolean 型で返却
    val isRental: Boolean
        get() = rental != null
}