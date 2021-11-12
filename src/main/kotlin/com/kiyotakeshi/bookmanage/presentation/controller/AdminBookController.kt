package com.kiyotakeshi.bookmanage.presentation.controller

import com.kiyotakeshi.bookmanage.application.service.AdminBookService
import com.kiyotakeshi.bookmanage.domain.model.Book
import com.kiyotakeshi.bookmanage.presentation.form.RegisterBookRequest
import com.kiyotakeshi.bookmanage.presentation.form.UpdateBookRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("admin/books")
class AdminBookController(
    private val adminBookService: AdminBookService
) {
    @PostMapping
    fun register(@RequestBody request: RegisterBookRequest) {
        adminBookService.register(
            Book(
                request.id,
                request.title,
                request.author,
                request.releaseDate
            )
        )
    }

    @PutMapping("/{bookId}")
    fun update(@PathVariable("bookId") bookId: Long, @RequestBody request: UpdateBookRequest) {
        adminBookService.update(bookId, request.title, request.author, request.releaseDate)
    }

    @DeleteMapping("/{bookId}")
    fun delete(@PathVariable("bookId") bookId: Long) {
        adminBookService.delete(bookId)
    }
}