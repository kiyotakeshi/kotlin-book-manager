/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.kiyotakeshi.bookmanage.infrastructure.database.record

import java.time.LocalDateTime

data class RentalRecord(
    var bookId: Long? = null,
    var userId: Long? = null,
    var rentalDatetime: LocalDateTime? = null,
    var returnDeadline: LocalDateTime? = null
)