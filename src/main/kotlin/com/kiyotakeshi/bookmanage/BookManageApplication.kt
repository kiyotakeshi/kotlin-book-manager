package com.kiyotakeshi.bookmanage

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import springfox.documentation.swagger2.annotations.EnableSwagger2

@EnableSwagger2
@SpringBootApplication
class BookManageApplication

fun main(args: Array<String>) {
	runApplication<BookManageApplication>(*args)
}
