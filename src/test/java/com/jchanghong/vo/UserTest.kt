package com.jchanghong.vo

import org.junit.Test

import org.junit.Assert.*

class UserTest {
    @Test
    fun getUsername() {
        var user = User(0, "root", "pass")
        assertEquals(user.username,"root")
        println(user.toString())

    }

}