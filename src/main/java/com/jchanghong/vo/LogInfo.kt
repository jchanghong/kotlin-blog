package com.jchanghong.vo

import org.apache.ibatis.type.Alias
import java.io.Serializable
import java.util.*

/**
 * @author Do
 * *
 * @package com.eumji.zblog.vo
 * *
 * @name LogInfo
 * *
 * @date 2017/4/10
 * *
 * @time 18:14
 */
@Alias("log")
data class LogInfo(var id: String = "0",
                   var userId: String="",
                   var url: String="",
                   var ip: String="",
                   var method: String="",
                   var args: String="",
                   var classMethod: String="",
                   var exception: String="",
                   var operateTime: Date = Date()) : Serializable
