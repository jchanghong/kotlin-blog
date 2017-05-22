package com.jchanghong.util

import java.security.MessageDigest


/**
 * md5加密工具类
 * FILE: Md5Util.java
 * MOTTO:  不积跬步无以至千里,不积小流无以至千里
 * AUTHOR: EumJi
 * DATE: 2017/4/9
 * TIME: 15:34
 */
object Md5Util {
    val PWD_CONST = "EumJi025"
    fun pwdDigest(password: String): String {
        return digest(password + PWD_CONST)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(pwdDigest("1"))
    }
    private fun digest(s: String): String {
        val hexDigits = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')
        try {
            val btInput = s.toByteArray()
            // 获得MD5摘要算法的 MessageDigest 对象
            val mdInst = MessageDigest.getInstance("MD5")
            // 使用指定的字节更新摘要
            mdInst.update(btInput)
            // 获得密文
            val md = mdInst.digest()
            // 把密文转换成十六进制的字符串形式
            val j = md.size
            val str = CharArray(j * 2)
            var k = 0
            for (i in 0..j - 1) {
                val byte0 = md[i]
                str[k++] = hexDigits[byte0.toInt().ushr(4) and 0xf]
                str[k++] = hexDigits[byte0.toInt() and 0xf]
            }
            return String(str)
        } catch (e: Exception) {
            e.printStackTrace()
            return s
        }

    }

}
