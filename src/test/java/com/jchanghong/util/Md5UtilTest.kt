package com.jchanghong.util

import org.junit.Test

import org.junit.Assert.*

class Md5UtilTest {
    @Test
    fun pwdDigest() {
        assertEquals(Md5Util.pwdDigest("1"),Md5Util.pwdDigest("1"))
    }

}