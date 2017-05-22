package com.jchanghong.util

/**
 * REST请求相应体
 * FILE: ResultInfo.java
 * MOTTO:  不积跬步无以至千里,不积小流无以至千里
 * AUTHOR: EumJi
 * DATE: 2017/4/9
 * TIME: 15:34
 */
data class ResultInfo(// 操作结果
        var resultCode: String, // 错误信息
        var errorInfo: String, // 附属对象
        var body: Any? = null)

