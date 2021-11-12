package com.kiyotakeshi.bookmanage.infrastructure.database.repository

import com.kiyotakeshi.bookmanage.domain.model.User
import com.kiyotakeshi.bookmanage.domain.repository.UserRepository
import com.kiyotakeshi.bookmanage.infrastructure.database.mapper.UserDynamicSqlSupport
import com.kiyotakeshi.bookmanage.infrastructure.database.mapper.UserMapper
import com.kiyotakeshi.bookmanage.infrastructure.database.record.UserRecord
import com.kiyotakeshi.bookmanage.infrastructure.database.mapper.selectOne
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.springframework.stereotype.Repository

@Suppress("SpringJavaInjectionPointsAutowiringInspection")
@Repository
class UserRepositoryImpl(
    private val mapper: UserMapper
) : UserRepository {
    override fun find(email: String): User? {
        val record = mapper.selectOne {
            where(UserDynamicSqlSupport.User.email, isEqualTo(email))
        }
        return record?.let { toModel(it) }
    }

    private fun toModel(record: UserRecord): User {
        return User(
            record.id!!,
            record.email!!,
            record.password!!,
            record.name!!,
            record.roleType!!
        )
    }
}