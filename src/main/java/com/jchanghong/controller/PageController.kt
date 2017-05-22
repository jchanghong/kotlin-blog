package com.jchanghong.controller

import com.jchanghong.service.ArticleService
import com.jchanghong.service.CategoryService
import com.jchanghong.service.PartnerService
import com.jchanghong.service.TagService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

import javax.annotation.Resource

/**
 * 首页入口controller
 * FILE: com.eumji.zblog.controller.IndexController.java
 * MOTTO:  不积跬步无以至千里,不积小流无以至千里
 * AUTHOR: EumJi
 * DATE: 2017/4/8
 * TIME: 15:19
 */
@Controller
class PageController {

    @Resource
    lateinit private var partnerService: PartnerService

    @Resource
    lateinit private var articleService: ArticleService
    @Resource
    lateinit private var categoryService: CategoryService
    @Resource
    lateinit private var tagService: TagService

    /**
     * 首页
     * @param model
     * *
     * @return
     */
    @RequestMapping("/")
    fun home(model: Model): String {
        val partnerList = partnerService.findAll()
        val categoryList = categoryService.initCategoryList()
        val articleCount = articleService.articleCount
        val archiveList = articleService.articleArchiveList()
        val tagCount = tagService.tagCount
        model.addAttribute("categoryCount", categoryList.size)
        model.addAttribute("articleCount", articleCount)
        model.addAttribute("tagCount", tagCount)
        model.addAttribute("categoryList", categoryList)
        model.addAttribute("partnerList", partnerList)
        model.addAttribute("archiveList", archiveList)
        return "index"
    }

    /**
     * 分类排序 暂时停用
     * @return
     */
    @RequestMapping("/archives")
    @Deprecated("")
    fun archivesPage(): String {
        return "archives"
    }

    @RequestMapping("/login")
    fun loginPage(): String {
        return "login"
    }

    /**跳转到友链展示页面
     * @return
     */
    @RequestMapping("/partner/list")
    fun partnerPage(): String {
        return "admin/partner/partnerList"
    }


    @RequestMapping("/about/me")
    fun aboutMe(model: Model): String {
        val partnerList = partnerService.findAll()
        val categoryList = categoryService.initCategoryList()
        val articleCount = articleService.articleCount
        val tagCount = tagService.tagCount
        model.addAttribute("categoryCount", categoryList.size)
        model.addAttribute("articleCount", articleCount)
        model.addAttribute("tagCount", tagCount)
        model.addAttribute("categoryList", categoryList)
        model.addAttribute("partnerList", partnerList)
        return "aboutMe"
    }

    @RequestMapping("/popular")
    fun popularArticle(model: Model): String {
        val partnerList = partnerService.findAll()
        val categoryList = categoryService.initCategoryList()
        val articleCount = articleService.articleCount
        val articleList = articleService.popularArticle()
        val tagCount = tagService.tagCount

        model.addAttribute("categoryCount", categoryList.size)
        model.addAttribute("articleCount", articleCount)
        model.addAttribute("tagCount", tagCount)
        model.addAttribute("articleList", articleList)
        model.addAttribute("categoryList", categoryList)
        model.addAttribute("partnerList", partnerList)
        return "popular"
    }

    @RequestMapping("/thymeleaf")
    fun thymeleafPage(): String {
        return "thymeleaf"
    }
}
