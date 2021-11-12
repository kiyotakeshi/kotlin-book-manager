package com.kiyotakeshi.bookmanage

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.regex.Pattern

class PasswordEncodingTest {

    private val password = "1qazxsw2"

    @Test
    @DisplayName("generated same hash using same salt")
    fun testBcrypt() {
        // @ref https://www.baeldung.com/spring-security-registration-password-encoding-bcrypt
        val bcrypt: PasswordEncoder = BCryptPasswordEncoder()
        val bcryptEncode1 = bcrypt.encode(password)
        val bcryptEncode2 = bcrypt.encode(password)
        assertNotEquals(bcryptEncode1, bcryptEncode2)

        val customEncoder: PasswordEncoder = CustomEncoder()
        val customEncode1 = customEncoder.encode(password)
        val customEncode2 = customEncoder.encode(password)
        assertEquals(customEncode1, customEncode2)

        // > BCrypt, however, will internally generate a random salt instead. This is important to understand because it means that each call will have a different result
        // > The “2a” represents the BCrypt algorithm version
        // > The “10” represents the strength of the algorithm
        // > The “Vt2ycIsscotk3diW/6RsHO” part is actually the randomly generated salt.
        // Basically, the first 22 characters are salt. The remaining part of the last field is the actual hashed version of the plain text
        // ex.) $2a$10$Vt2ycIsscotk3diW/6RsHO/xtwLJbbg9pLD59qckrCEv7xqf4A296
        // println(bcrypt.encode(password))
    }
}