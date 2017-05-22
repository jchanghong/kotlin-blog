package com.jchanghong.service.impl

import com.jchanghong.mapper.UserMapper
import com.jchanghong.service.UserService
import com.jchanghong.vo.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.annotation.Resource

/**
 * Created by GeneratorFx on 2017-04-11.
 */
@Service
@Transactional
class UserServiceImpl : UserService {

    @Resource
    lateinit private var userMapper: UserMapper

    override fun loadUserByUsername(username: String): User {
        return userMapper.getUser(username)
    }
}


