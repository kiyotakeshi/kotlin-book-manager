package com.kiyotakeshi.bookmanage.presentation.controller

import com.kiyotakeshi.bookmanage.application.service.BookService
import com.kiyotakeshi.bookmanage.presentation.form.BookInfo
import com.kiyotakeshi.bookmanage.presentation.form.GetBookDetailResponse
import com.kiyotakeshi.bookmanage.presentation.form.GetBookListResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("books")
class BookController(
    private val bookService: BookService
) {
    @GetMapping
    fun getBooks(): GetBookListResponse {
        val bookList = bookService.getList().map {
            BookInfo(it)
        }
        return GetBookListResponse(bookList)
    }

    @GetMapping("/{bookId}")
    fun getDetail(@PathVariable("bookId")bookId: Long): GetBookDetailResponse {
        val book = bookService.getDetail(bookId)
        return GetBookDetailResponse(book)
    }
}