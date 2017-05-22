package com.jchanghong.vo

import org.apache.ibatis.type.Alias

import java.io.Serializable

/**
 * @author Do
 * *
 * @package com.eumji.zblog.vo
 * *
 * @name CategoryCustom
 * *
 * @date 2017/4/13
 * *
 * @time 12:30
 */
@Alias("categoryCustom")
data class CategoryCustom(var id: Int = 0,
                          var categoryName: String="",
                          var aliasName: String="",
                          var sort: Int = 0,
                          var articleCount: Int = 0) : Serializable
