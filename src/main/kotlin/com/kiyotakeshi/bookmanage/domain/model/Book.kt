package com.kiyotakeshi.bookmanage.domain.model

import java.time.LocalDate

// ドメインオブジェクト
data class Book(
    val id: Long,
    val title: String,
    val author: String,
    val releaseDate: LocalDate
)