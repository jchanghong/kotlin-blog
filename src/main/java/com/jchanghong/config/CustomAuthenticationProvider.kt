package com.jchanghong.config

import com.jchanghong.service.UserService
import com.jchanghong.util.Md5Util
import com.jchanghong.vo.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.*
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException

/**
 * FILE: CustomAuthenticationProvider.java
 * MOTTO:  不积跬步无以至千里,不积小流无以至千里
 * AUTHOR: EumJi
 * DATE: 2017/4/9
 * TIME: 10:07
 */
@Configuration
@EnableWebSecurity
class CustomAuthenticationProvider : AuthenticationProvider {
    @Autowired
    lateinit private var userService: UserService

    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication {
        val token = authentication as UsernamePasswordAuthenticationToken
        val username = token.name
        //从数据库找到的用户
        var user: User? = null
        if (username != null) {
            user = userService.loadUserByUsername(username)
        }
        //
        if (user == null) {
            throw UsernameNotFoundException("用户名/密码无效")
        } else if (user.isEnabled) {
            throw DisabledException("用户已被禁用")
        } else if (user.isExpired) {
            throw AccountExpiredException("账号已过期")
        } else if (user.isLocked) {
            throw LockedException("账号已被锁定")
        } else if (user.isCredential) {
            throw LockedException("凭证已过期")
        }
        //数据库用户的密码
        val password = user.password
        val pwdDigest = Md5Util.pwdDigest(token.credentials.toString())
        //与authentication里面的credentials相比较
        if (password != pwdDigest) {

            throw BadCredentialsException("Invalid username/password")
        }
        //授权
        return UsernamePasswordAuthenticationToken(user, password, user.authorities)
    }

    fun config(web: WebSecurity) {
        web.ignoring().antMatchers("/js/**", "/css/**", "/vendor/**", "/image/**", "/admin/**")
    }

    override fun supports(authentication: Class<*>): Boolean {
        return UsernamePasswordAuthenticationToken::class.java == authentication
    }
}


