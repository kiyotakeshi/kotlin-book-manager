package com.kiyotakeshi.bookmanage.presentation.controller

import com.kiyotakeshi.bookmanage.application.service.RentalService
import com.kiyotakeshi.bookmanage.application.service.security.BookManagerUserDetails
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rental")
class RentalController(
    private val rentalService: RentalService
) {
    @PostMapping("/{bookId}")
    fun startRental(@PathVariable("bookId") bookId: Long) {
        // as で cast
        val user =
            SecurityContextHolder.getContext().authentication.principal as BookManagerUserDetails
        rentalService.startRental(bookId, user.id)
    }

    @DeleteMapping("/{bookId}")
    fun endRental(@PathVariable("bookId") bookId: Long) {
        val user =
            SecurityContextHolder.getContext().authentication.principal as BookManagerUserDetails
        rentalService.endRental(bookId, user.id)
    }
}