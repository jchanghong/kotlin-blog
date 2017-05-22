package com.jchanghong.controller.admin

import com.jchanghong.service.ArticleService
import com.jchanghong.service.CategoryService
import com.jchanghong.service.PartnerService
import com.jchanghong.service.TagService
import com.jchanghong.vo.Pager
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

import javax.annotation.Resource

/**
 * FILE: AdminPageController.java
 * MOTTO:  不积跬步无以至千里,不积小流无以至千里
 * AUTHOR: EumJi
 * DATE: 2017/4/9
 * TIME: 21:55
 */
@Controller
@RequestMapping("/admin")
class AdminPageController {

    @Resource
    lateinit private var articleService: ArticleService

    @Resource
    lateinit private var categoryService: CategoryService

    @Resource
    lateinit private var tagService: TagService

    @Resource
    lateinit private var partnerService: PartnerService

    /**
     * 后台首页
     * @return
     */
    @RequestMapping("/home")
    fun homePage(): String {
        return "redirect:/admin/article/list"
    }

    /**
     * 跳转到文章列表页面
     * @return
     */
    @RequestMapping("/article/list")
    fun articlePage(model: Model): String {
        var tagList = tagService.tagList
        var categoryList = categoryService.categoryList
        model.addAttribute("tagList", tagList)
        model.addAttribute("categoryList", categoryList)
        return "admin/article/articleList"
    }


    @RequestMapping("/tag/list")
    fun labelPage(): String {
        return "admin/label/labelList"
    }

    /**
     * 加载友链分页
     * @param pager
     * *
     * @param model
     * *
     * @return
     */
    @RequestMapping("/partner/load")
    fun loadPartner(pager: Pager<*>, model: Model, param: String?): String {
        pager.start = (pager.page - 1) * pager.limit
        var partnerList = partnerService.loadPartner(pager, param?:"")
        model.addAttribute("partnerList", partnerList)
        return "admin/partner/partnerTable"
    }

    /**
     * 跳转添加友链页面
     * @return
     */
    @RequestMapping("/partner/addJump")
    fun partnerAddPage(): String {
        return "admin/partner/partnerAdd"
    }

    @RequestMapping("/partner/editJump/{id}")
    fun partnerEditPage(@PathVariable id: Int?, model: Model): String {
        var partner = partnerService.getPartnerById(id ?: 0)
        model.addAttribute("partner", partner)
        return "admin/partner/partnerEdit"
    }

    /**跳转到友链展示页面
     * @return
     */
    @RequestMapping("/partner/list")
    fun partnerPage(): String {
        return "admin/partner/partnerList"
    }
}
