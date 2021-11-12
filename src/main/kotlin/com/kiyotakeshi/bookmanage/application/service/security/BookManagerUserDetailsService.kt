package com.kiyotakeshi.bookmanage.application.service.security

import com.kiyotakeshi.bookmanage.application.service.AuthenticationService
import com.kiyotakeshi.bookmanage.domain.enum.RoleType
import com.kiyotakeshi.bookmanage.domain.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

class BookManagerUserDetailsService(
    private val authenticationService: AuthenticationService
) : UserDetailsService {

    // ユーザ名を引数で受け取り、ユーザ情報を取得
    // UserDetails 型のオブジェクトを使用し、パスワードの比較や認可処理をフレームワーク側で実現
    override fun loadUserByUsername(username: String): UserDetails? {
        val user = authenticationService.findUser(username)
        return user?.let { BookManagerUserDetails(user) }
    }
}

data class BookManagerUserDetails(val id: Long, val email: String, val pass: String, val roleType: RoleType) :
    UserDetails {
    constructor(user: User) : this(user.id, user.email, user.password, user.roleType)

    // 認可が必要なパスの場合、この関数で取得した権限の情報でチェックされる
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return AuthorityUtils.createAuthorityList(this.roleType.toString())
    }

    override fun getPassword(): String {
        return this.pass
    }

    override fun getUsername(): String {
        return this.email
    }

    override fun isAccountNonExpired(): Boolean {
        return true;
    }

    override fun isAccountNonLocked(): Boolean {
        return true;
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true;
    }

    override fun isEnabled(): Boolean {
        return true;
    }
}
