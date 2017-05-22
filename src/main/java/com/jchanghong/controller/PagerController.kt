package com.jchanghong.controller

import com.jchanghong.service.CategoryService
import com.jchanghong.service.PagerService
import com.jchanghong.service.TagService
import com.jchanghong.vo.Pager
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

import javax.annotation.Resource

/**
 * @author Do
 * *
 * @package com.eumji.zblog.controller.admin
 * *
 * @name PagerController
 * *
 * @date 2017/4/11
 * *
 * @time 21:44
 */
@RestController
class PagerController {

    @Resource
    lateinit private var pagerService: PagerService
    @Resource
    lateinit private var tagService: TagService
    @Resource
    lateinit internal var categoryService: CategoryService

    /**
     * 初始化文章分页信息
     * @return
     */
    @RequestMapping("/pager/articles/load")
    fun loadArticlePager(pager: Pager<*>): Pager<*> {
        pagerService.initPage(pager)
        return pager
    }

    /**
     * 初始化当前分类id的文章分页信息
     * @param pager 分页对象
     * *
     * @param categoryId 分类id
     * *
     * @return
     */
    @RequestMapping("/pager/categories/{categoryId}")
    fun loadCategoryPager(pager: Pager<*>, @PathVariable categoryId: Int?): Pager<*> {
        pagerService.loadCategoryPager(pager, categoryId ?: 0)
        return pager
    }

    /**
     * 初始化当前标签的文章分页信息
     * @param pager 分页对象
     * *
     * @param tagId 标签
     * *
     * @return
     */
    @RequestMapping("/pager/tags/{tagId}")
    @ResponseBody
    fun initPage(pager: Pager<*>, @PathVariable tagId: Int?): Pager<*> {
        tagService.ArticleTagPage(pager, tagId ?: 0)
        return pager
    }

}
