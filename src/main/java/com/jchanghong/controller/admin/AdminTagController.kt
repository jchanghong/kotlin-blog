package com.jchanghong.controller.admin

import com.jchanghong.service.TagService
import com.jchanghong.util.ResultInfo
import com.jchanghong.util.ResultInfoFactory
import com.jchanghong.vo.Pager
import com.jchanghong.vo.Tag
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import javax.annotation.Resource

/**
 * FILE: com.eumji.zblog.controller.admin.TagController.java
 * MOTTO:  不积跬步无以至千里,不积小流无以至千里
 * AUTHOR: EumJi
 * DATE: 2017/4/15
 * TIME: 11:31
 */
@Controller
@RequestMapping("/admin/tag")
class AdminTagController {

    @Resource
    lateinit private var tagService: TagService


    @RequestMapping("/initPage")
    @ResponseBody
    fun initPage(pager: Pager<*>, model: Model): Pager<*> {
        tagService.initPage(pager)
        return pager
    }

    @RequestMapping("/editJump/{id}")
    fun editPage(@PathVariable id: Int?, model: Model): String {
        val tag = tagService.getTagById(id ?: 0)
        model.addAttribute("tag", tag)
        return "admin/label/labelEdit"
    }

    @RequestMapping("/addJump")
    fun addPage(): String {
        return "admin/label/labelAdd"
    }

    @RequestMapping("/load")
    fun loadTagList(pager: Pager<*>, tagName: String?, model: Model): String {
        val tagList = tagService.loadTagList(pager, tagName?:"")
        println(tagList)
        model.addAttribute("tagList", tagList)
        return "admin/label/labelTable"
    }


    @RequestMapping("/save")
    @ResponseBody
    fun saveTag(tag: Tag): ResultInfo {
        try {
            tag.aliasName = URLDecoder.decode(tag.aliasName, "UTF-8")
            tag.tagName = URLDecoder.decode(tag.tagName, "UTF-8")
            if (tagService.checkExist(tag)) {
                return ResultInfoFactory.getErrorResultInfo("标签名或别名已经存在")
            }
            tagService.saveTag(tag)
        } catch (e: UnsupportedEncodingException) {
            ResultInfoFactory.getErrorResultInfo("添加失败,字符串格式化错误")
        }

        return ResultInfoFactory.successResultInfo
    }

    @RequestMapping("update")
    @ResponseBody
    fun updateTag(tag: Tag): ResultInfo {
        if (tagService.checkExist(tag)) {
            return ResultInfoFactory.getErrorResultInfo("已存在相同的标签名或者别名")
        }
        tagService.updateTag(tag)
        return ResultInfoFactory.successResultInfo
    }
}
