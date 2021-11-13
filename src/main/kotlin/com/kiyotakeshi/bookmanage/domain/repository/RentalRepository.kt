package com.kiyotakeshi.bookmanage.domain.repository

import com.kiyotakeshi.bookmanage.domain.model.Rental

interface RentalRepository {
    fun startRental(rental: Rental)
    fun endRental(bookId: Long)
}