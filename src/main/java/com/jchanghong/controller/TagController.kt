package com.jchanghong.controller

import com.jchanghong.service.TagService
import com.jchanghong.vo.Pager
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

import javax.annotation.Resource

/**
 * @author Do
 * *
 * @package com.eumji.zblog.controller.admin
 * *
 * @name TagController
 * *
 * @date 2017/4/13
 * *
 * @time 18:54
 */
@Controller
@RequestMapping("/tags")
class TagController {

    @Resource
    lateinit private var tagService: TagService

    /**
     * 通过tag获取文章列表
     * @param pager 分页信息
     * *
     * @param tagId 标签id
     * *
     * @param model 数据视图
     * *
     * @return
     */
    @RequestMapping("/load/{tagId}")
    fun loadArticleByTag(pager: Pager<*>, @PathVariable tagId: Int?, model: Model): String {
        val articleList = tagService.loadArticleByTag(pager, tagId ?: 0)
        if (!articleList.isEmpty()) {
            model.addAttribute("articleList", articleList)
            model.addAttribute("pager", pager)
            //2017-05-07修复获取tag名称错误的问题,不应该从articlelist中取,因为每篇文章可能有多个tag
            model.addAttribute("tagName", tagService.getTagById(tagId ?: 0)?.tagName)
        }

        return "blog/part/tagSummary"
    }

}

