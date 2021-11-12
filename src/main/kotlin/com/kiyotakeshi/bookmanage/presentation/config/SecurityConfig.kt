package com.kiyotakeshi.bookmanage.presentation.config

import com.kiyotakeshi.bookmanage.CustomEncoder
import com.kiyotakeshi.bookmanage.application.service.AuthenticationService
import com.kiyotakeshi.bookmanage.application.service.security.BookManagerUserDetailsService
import com.kiyotakeshi.bookmanage.domain.enum.RoleType
import com.kiyotakeshi.bookmanage.presentation.handler.BookManagerAccessDeniedHandler
import com.kiyotakeshi.bookmanage.presentation.handler.BookManagerAuthenticationEntryPoint
import com.kiyotakeshi.bookmanage.presentation.handler.BookManagerAuthenticationFailureHandler
import com.kiyotakeshi.bookmanage.presentation.handler.BookManagerAuthenticationSuccessHandler
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@EnableWebSecurity
class SecurityConfig(private val authenticationService: AuthenticationService) : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .mvcMatchers(
                "/login",
                "/swagger-ui/**",
                "/v3/api-docs",
                "/swagger-resources/**",
            ).permitAll()
            .mvcMatchers("/admin/**").hasAuthority(RoleType.ADMIN.toString())
            .anyRequest().authenticated()
            .and()
            .csrf().disable()
            .formLogin()
            .loginProcessingUrl("/login")
            .usernameParameter("email")
            .passwordParameter("pass")
            .successHandler(BookManagerAuthenticationSuccessHandler())
            .failureHandler(BookManagerAuthenticationFailureHandler()) // 認証失敗時のハンドラ
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(BookManagerAuthenticationEntryPoint()) // 未認証時のハンドラ
            .accessDeniedHandler(BookManagerAccessDeniedHandler()) // 認可失敗時のハンドラ
            .and()
            .cors()
            .configurationSource(corsConfigurationSource())
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        // 認証処理を実装したクラスを引数に渡す
        auth.userDetailsService(BookManagerUserDetailsService(authenticationService))
             .passwordEncoder(BCryptPasswordEncoder())
    }

    private fun corsConfigurationSource(): CorsConfigurationSource {
        val corsConfiguration = CorsConfiguration()
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL)
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL)
        corsConfiguration.addAllowedOrigin("http://localhost:3000")
        corsConfiguration.allowCredentials = true

        val corsConfigurationSource = UrlBasedCorsConfigurationSource()
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration)

        return corsConfigurationSource
    }
}