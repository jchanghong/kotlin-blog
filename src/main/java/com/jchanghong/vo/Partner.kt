package com.jchanghong.vo

import org.apache.ibatis.type.Alias
import java.io.Serializable

/**
 * Created by GeneratorFx on 2017-04-10.
 */
@Alias("partner")
data class Partner(var id: Int = 0,
                   var siteName: String="",
                   var siteUrl: String="",
                   var siteDesc: String="",
                   var sort: Int = 0) : Serializable

