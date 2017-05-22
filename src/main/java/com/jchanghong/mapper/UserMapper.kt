package com.jchanghong.mapper

import com.jchanghong.vo.User
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

/**
 * FILE: UserMapper.java
 * MOTTO:  不积跬步无以至千里,不积小流无以至千里
 * AUTHOR: EumJi
 * DATE: 2017/4/9
 * TIME: 10:20
 */
@Mapper
interface UserMapper {
    /**
     * 获取用户凭证
     * @param username 账号
     * *
     * @return
     */
    fun getUser(@Param("username") username: String): User

    fun allUser(): List<User>
}
