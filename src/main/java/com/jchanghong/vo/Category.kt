package com.jchanghong.vo

import org.apache.ibatis.type.Alias

import java.io.Serializable

/**
 * Created by GeneratorFx on 2017-04-11.
 */
@Alias("category")
data class Category(var id: Int = 0,
                    var categoryName: String="",
                    var aliasName: String="",
                    var sort: Int = 0) : Serializable
