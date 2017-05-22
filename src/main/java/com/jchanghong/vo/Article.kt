package com.jchanghong.vo

import org.apache.ibatis.type.Alias
import java.io.Serializable
import java.util.*

/**
 * Created by GeneratorFx on 2017-04-11.
 */
@Alias("article")
data class Article(var id: Int = 0,
                   var categoryId: Int = 0,
                   var categoryName: String="",
                   var title: String=""
                   , var content: String="",
                   var description: String="",
                   var status: Int=1,
                   var author: String="",
                   var createTime: Date = Date(),
                   var updateTime: Date = Date(),
                   var showCount: Int = 0) : Serializable
