package com.jchanghong.vo

import org.apache.ibatis.type.Alias
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.io.Serializable
import java.util.*

@Alias("user")
data class User(var id: Long = 0,
                var username: String="",
                var password: String="", //过期
                var isExpired: Boolean = false, //被锁
                var isLocked: Boolean = false, //凭证
                var isCredential: Boolean = false, //禁用
                var isEnabled: Boolean = false,
                var createTime: Date = Date()) : Serializable {


    val authorities: Collection<GrantedAuthority>
        get() {
            val authorities = ArrayList<GrantedAuthority>()
            authorities.add(SimpleGrantedAuthority("ROLE_ADMIN"))
            return authorities
        }

}