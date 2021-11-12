package com.kiyotakeshi.bookmanage.infrastructure.database.repository

import com.kiyotakeshi.bookmanage.domain.model.Book
import com.kiyotakeshi.bookmanage.domain.model.BookWithRental
import com.kiyotakeshi.bookmanage.domain.model.Rental
import com.kiyotakeshi.bookmanage.domain.repository.BookRepository
import com.kiyotakeshi.bookmanage.infrastructure.database.mapper.BookMapper
import com.kiyotakeshi.bookmanage.infrastructure.database.mapper.custom.BookWithRentalMapper
import com.kiyotakeshi.bookmanage.infrastructure.database.mapper.custom.select
import com.kiyotakeshi.bookmanage.infrastructure.database.mapper.custom.selectByPrimaryKey
import com.kiyotakeshi.bookmanage.infrastructure.database.mapper.deleteByPrimaryKey
import com.kiyotakeshi.bookmanage.infrastructure.database.mapper.insert
import com.kiyotakeshi.bookmanage.infrastructure.database.mapper.updateByPrimaryKeySelective
import com.kiyotakeshi.bookmanage.infrastructure.database.record.BookRecord
import com.kiyotakeshi.bookmanage.infrastructure.database.record.custom.BookWithRentalRecord
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Suppress("SpringJavaInjectionPointsAutowiringInspection")
@Repository
class BookRepositoryImpl(
    private val bookWithRentalMapper: BookWithRentalMapper,
    private val bookMapper: BookMapper
) : BookRepository {
    override fun findAllWithRental(): List<BookWithRental> {
        return bookWithRentalMapper.select().map { toModel(it) }
    }

    override fun findWithRental(id: Long): BookWithRental? {
        // 安全呼び出し(?.) と let を組み合わせてデータが取得できない場合は null を返す
        // if(hoge != null) に当たる
        return bookWithRentalMapper.selectByPrimaryKey(id)?.let { toModel(it) }
    }

    override fun register(book: Book) {
        bookMapper.insert(toRecord(book))
    }

    // id 以外は null 許容
    override fun update(id: Long, title: String?, author: String?, releaseDate: LocalDate?) {
        bookMapper.updateByPrimaryKeySelective(BookRecord(id, title, author, releaseDate))
    }

    override fun delete(id: Long) {
        bookMapper.deleteByPrimaryKey(id)
    }

    private fun toRecord(model: Book): BookRecord {
        return BookRecord(model.id, model.title, model.author, model.releaseDate)
    }

    // BookWithRentalRecord は MyBatis の仕様に依存したクラスのため変換する
    private fun toModel(record: BookWithRentalRecord): BookWithRental {
        val book = Book(
            record.id!!,
            record.title!!,
            record.author!!,
            record.releaseDate!!
        )
        val rental = record.userId?.let {
            Rental(
                record.id!!,
                record.userId!!,
                record.rentalDatetime!!,
                record.returnDeadline!!,
            )
        }
        return BookWithRental(book, rental)
    }
}