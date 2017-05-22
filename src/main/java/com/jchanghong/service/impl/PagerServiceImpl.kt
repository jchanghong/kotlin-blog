package com.jchanghong.service.impl

import com.jchanghong.mapper.ArticleMapper
import com.jchanghong.mapper.PagerMapper
import com.jchanghong.service.PagerService
import com.jchanghong.vo.Pager
import org.springframework.stereotype.Service

import javax.annotation.Resource

/**
 * @author Do
 * *
 * @package com.eumji.zblog.service.impl
 * *
 * @name PagerServiceImpl
 * *
 * @date 2017/4/11
 * *
 * @time 21:46
 */
@Service
class PagerServiceImpl : PagerService {

    @Resource
    lateinit private var articleMapper: ArticleMapper
    @Resource
    lateinit private var pagerMapper: PagerMapper

    override fun initPage(pager: Pager<*>) {
        val count = articleMapper.articleCount
        pager.totalCount = count
    }

    override fun loadCategoryPager(pager: Pager<*>, categoryId: Int) {
        val count = pagerMapper.loadCategoryPager(categoryId)
        pager.totalCount = count
    }

    override fun loadTagPager(pager: Pager<*>, tagId: Int) {
        val count = pagerMapper.loadTagPager(tagId)
        pager.totalCount = count
    }
}
