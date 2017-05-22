package com.jchanghong.controller.admin

import com.jchanghong.service.CategoryService
import com.jchanghong.vo.Pager
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import java.util.*

import javax.annotation.Resource

/**
 * FILE: ArchiveController.java
 * MOTTO:  不积跬步无以至千里,不积小流无以至千里
 * AUTHOR: EumJi
 * DATE: 2017/5/8
 * TIME: 15:15
 */
@Controller
class ArchiveController {

    @Resource
    lateinit private var categoryService: CategoryService

    /*文章归档列表*/
    @RequestMapping("/createTime/load/{createTime}")
    fun categoryList(@PathVariable createTime: String?, pager: Pager<*>, model: Model): String {
        val articleList = categoryService.loadArticleByArchive(createTime?:Date().toString(), pager)
        if (!articleList.isEmpty()) {
            model.addAttribute("articleList", articleList)
            model.addAttribute("pager", pager)
            model.addAttribute("categoryName", articleList[0].categoryName)
        }
        return "blog/part/categorySummary"
    }
}
