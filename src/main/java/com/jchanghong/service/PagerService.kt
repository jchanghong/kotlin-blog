package com.jchanghong.service

import com.jchanghong.vo.Pager

/**
 * @author Do
 * *
 * @package com.eumji.zblog.service
 * *
 * @name PagerService
 * *
 * @date 2017/4/11
 * *
 * @time 21:46
 */
interface PagerService {
    /**
     * 初始化分页信息
     * @return
     * *
     * @param pager
     */
    fun initPage(pager: Pager<*>)

    /**
     * 初始化某个category的分页信息
     * @param pager
     * *
     * @param categoryId
     */
    fun loadCategoryPager(pager: Pager<*>, categoryId: Int)

    /**
     * 初始化某个tag的分页信息
     * @param pager
     * *
     * @param tagId
     */
    fun loadTagPager(pager: Pager<*>, tagId: Int)
}
