/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.kiyotakeshi.bookmanage.infrastructure.database.mapper

import com.kiyotakeshi.bookmanage.domain.enum.RoleType
import java.sql.JDBCType
import org.mybatis.dynamic.sql.SqlTable

object UserDynamicSqlSupport {
    object User : SqlTable("user") {
        val id = column<Long>("id", JDBCType.BIGINT)

        val email = column<String>("email", JDBCType.VARCHAR)

        val password = column<String>("password", JDBCType.VARCHAR)

        val name = column<String>("name", JDBCType.VARCHAR)

        val roleType = column<RoleType>("role_type", JDBCType.CHAR, "org.apache.ibatis.type.EnumTypeHandler")
    }
}