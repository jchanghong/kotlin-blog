package com.jchanghong.service

import com.jchanghong.vo.ArticleCustom
import com.jchanghong.vo.Pager
import com.jchanghong.vo.Tag

/**
 * @author Do
 * *
 * @package com.eumji.zblog.service
 * *
 * @name TagService
 * *
 * @date 2017/4/13
 * *
 * @time 18:55
 */
interface TagService {
    /**
     * 获取当前tag下的文章列表
     * @param pager
     * *
     * @param tagId
     * *
     * @return
     */
    fun loadArticleByTag(pager: Pager<*>, tagId: Int): List<ArticleCustom>

    val tagCount: Int

    fun getTagById(id: Int): Tag?

    fun loadTagList(pager: Pager<*>, tagName: String): List<Tag>

    fun saveTag(tag: Tag)

    fun checkExist(tag: Tag): Boolean

    fun updateTag(tag: Tag)

    fun initPage(pager: Pager<*>)

    val tagList: List<Tag>

    /**
     * 初始化分页
     * @param pager
     * *
     * @param tagId
     */
    fun ArticleTagPage(pager: Pager<*>, tagId: Int)
}
