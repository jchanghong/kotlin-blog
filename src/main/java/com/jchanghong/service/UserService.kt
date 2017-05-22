package com.jchanghong.service


import com.jchanghong.vo.User

/**
 * Created by GeneratorFx on 2017-04-11.
 */
interface UserService {


    fun loadUserByUsername(username: String): User?
}
