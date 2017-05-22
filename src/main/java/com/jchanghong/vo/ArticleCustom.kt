package com.jchanghong.vo

import org.apache.ibatis.type.Alias
import java.io.Serializable
import java.util.*

/**
 * @author Do
 * *
 * @package com.eumji.zblog.vo
 * *
 * @name ArticleCustom
 * *
 * @date 2017/4/12
 * *
 * @time 12:34
 */
@Alias("articleCutsom")
data class ArticleCustom(var id: Int = 0,
                         var categoryId: Int = 0,
                         var categoryName: String="",
        //分类名称), //分类别名
                         var categoryAliasName: String="",
                         var title: String="",
                         var content: String="",
                         var description: String="",
                         var status: Int = 1,
                         var author: String="",
                         var createTime: Date = Date(),
                         var updateTime: Date = Date(),
                         var showCount: Int = 0,
                         var tagList: List<ArticleTag> = emptyList()) : Serializable