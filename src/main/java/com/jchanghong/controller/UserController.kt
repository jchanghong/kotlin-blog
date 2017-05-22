package com.jchanghong.controller

import com.jchanghong.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

import javax.annotation.Resource

/**
 * Created by GeneratorFx on 2017-04-11.
 */
@Controller
@RequestMapping("/user")
class UserController {

    @Resource
    lateinit internal var userService: UserService


}
