package com.jchanghong.controller.admin

import com.jchanghong.service.UserService
import com.jchanghong.util.Md5Util
import com.jchanghong.util.ResultInfo
import com.jchanghong.util.ResultInfoFactory
import com.jchanghong.vo.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import javax.servlet.http.HttpSession

/**
 * FILE: LoginController.java
 * MOTTO:  不积跬步无以至千里,不积小流无以至千里
 * AUTHOR: EumJi
 * DATE: 2017/4/9
 * TIME: 15:32
 */
@RestController
class LoginController {

    @Autowired
    lateinit private var userService: UserService

    @RequestMapping(value = "/login/auth", method = arrayOf(RequestMethod.POST))
    fun loginAuth(user: User, session: HttpSession): ResultInfo {
        var resultInfo: ResultInfo
        println(Md5Util.pwdDigest(user.password))
        println("user:" + user)
        val userInfo = userService.loadUserByUsername(user.username)
        if (userInfo == null) {
            resultInfo = ResultInfoFactory.getErrorResultInfo("账号不存在")
        } else {
            if (userInfo.password == Md5Util.pwdDigest(user.password)) {
                resultInfo = ResultInfoFactory.successResultInfo
            } else {
                resultInfo = ResultInfoFactory.getErrorResultInfo("账号或密码错误")
            }
            session.setAttribute("user", userInfo)
        }

        return resultInfo

    }
}
