package com.kiyotakeshi.bookmanage.infrastructure.database.repository

import com.kiyotakeshi.bookmanage.domain.model.Rental
import com.kiyotakeshi.bookmanage.domain.repository.RentalRepository
import com.kiyotakeshi.bookmanage.infrastructure.database.mapper.RentalMapper
import com.kiyotakeshi.bookmanage.infrastructure.database.mapper.deleteByPrimaryKey
import com.kiyotakeshi.bookmanage.infrastructure.database.mapper.insert
import com.kiyotakeshi.bookmanage.infrastructure.database.record.RentalRecord
import org.springframework.stereotype.Repository

@Suppress("SpringJavaInjectionPointsAutowiringInspection")
@Repository
class RentalRepositoryImpl(
    private val rentalMapper: RentalMapper
) : RentalRepository {
    override fun startRental(rental: Rental) {
        rentalMapper.insert(toRecord(rental))
    }

    override fun endRental(bookId: Long) {
        rentalMapper.deleteByPrimaryKey(bookId)
    }

    private fun toRecord(model: Rental): RentalRecord {
        return RentalRecord(model.bookId, model.userId, model.rentalDatetime, model.returnDeadline)
    }
}