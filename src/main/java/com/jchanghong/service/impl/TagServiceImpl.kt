package com.jchanghong.service.impl

import com.jchanghong.mapper.ArticleMapper
import com.jchanghong.mapper.TagMapper
import com.jchanghong.service.TagService
import com.jchanghong.vo.ArticleCustom
import com.jchanghong.vo.Pager
import com.jchanghong.vo.Tag
import org.springframework.stereotype.Service

import javax.annotation.Resource

/**
 * @author Do
 * *
 * @package com.eumji.zblog.service.impl
 * *
 * @name TagServiceImpl
 * *
 * @date 2017/4/13
 * *
 * @time 18:56
 */
@Service
class TagServiceImpl : TagService {
    @Resource
    lateinit private var articleMapper: ArticleMapper
    @Resource
    lateinit private var tagMapper: TagMapper

    override fun loadArticleByTag(pager: Pager<*>, tagId: Int): List<ArticleCustom> {
        return articleMapper.loadArticleByTag(pager, tagId)
    }

    override val tagCount: Int
        get() = tagMapper.tagCount

    override fun getTagById(id: Int): Tag? {
        return tagMapper.getTagById(id)
    }

    override fun loadTagList(pager: Pager<*>, tagName: String): List<Tag> {
        pager.start = pager.start
        return tagMapper.loadTagList(pager, tagName)
    }

    override fun saveTag(tag: Tag) {
        tagMapper.saveTag(tag)
    }

    override fun checkExist(tag: Tag): Boolean {
        val count = tagMapper.checkExist(tag)
        if (count > 0) {
            return true
        }
        return false
    }

    override fun updateTag(tag: Tag) {
        tagMapper.updateTag(tag)
    }

    override fun initPage(pager: Pager<*>) {
        val count = tagMapper.initPage(pager)
        pager.totalCount = count
    }

    override val tagList: List<Tag>
        get() = tagMapper.tagList


    /**
     * 初始化tag -> article的分页
     * @param pager
     */
    override fun ArticleTagPage(pager: Pager<*>, tagId: Int) {
        val count = tagMapper.articleTagPage(tagId)
        pager.totalCount = count
    }
}
