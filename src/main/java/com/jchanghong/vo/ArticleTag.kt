package com.jchanghong.vo

import org.apache.ibatis.type.Alias

import java.io.Serializable

/**
 * Created by GeneratorFx on 2017-04-11.
 */
@Alias("articleTag")
data class ArticleTag(var articleId: Int = 0,
                      var tagId: Int = 0,
                      var tagName: String="") : Serializable
