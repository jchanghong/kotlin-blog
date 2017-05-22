package com.jchanghong.controller

import com.jchanghong.service.CategoryService
import com.jchanghong.service.PartnerService
import com.jchanghong.vo.Pager
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

import javax.annotation.Resource

/**
 * Created by GeneratorFx on 2017-04-11.
 */
@Controller
@RequestMapping("/categories")
class CategoryController {

    @Resource
    lateinit internal var categoryService: CategoryService
    @Resource
    lateinit internal var partnerService: PartnerService

    /**
     * 获取某个标签的分页文章
     * @param model
     * *
     * @param pager
     * *
     * @param categoryId
     * *
     * @return
     */
    @RequestMapping("/load/{categoryId}")
    fun loadArticleByCategory(model: Model, pager: Pager<*>, @PathVariable categoryId: Int?): String {
        val articleList = categoryService.loadArticleByCategory(pager, categoryId?:0)
        if (!articleList.isEmpty()) {
            model.addAttribute("articleList", articleList)
            model.addAttribute("pager", pager)
            model.addAttribute("categoryName", articleList[0].categoryName)
        }
        return "blog/part/categorySummary"
    }

    /**
     * 跳转到分类的页面 暂时停用
     * @param model
     * *
     * @param categoryId
     * *
     * @return
     */
    @Deprecated("")
    @RequestMapping("/details/{categoryId}")
    fun categoryPage(model: Model, @PathVariable categoryId: Int?): String {
        val partnerList = partnerService.findAll()
        model.addAttribute("partnerList", partnerList)
        model.addAttribute("categoryId", categoryId)
        return "category"
    }


}
