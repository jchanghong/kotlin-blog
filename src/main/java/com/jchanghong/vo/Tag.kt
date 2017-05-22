package com.jchanghong.vo

import org.apache.ibatis.type.Alias

import java.io.Serializable

/**
 * FILE: Tag.java
 * MOTTO:  不积跬步无以至千里,不积小流无以至千里
 * AUTHOR: EumJi
 * DATE: 2017/4/15
 * TIME: 11:36
 */
@Alias("tag")
data class Tag(var id: Int = 0, var tagName: String="", var aliasName: String="") : Serializable


