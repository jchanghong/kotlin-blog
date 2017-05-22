package com.jchanghong.mapper

import org.apache.ibatis.annotations.Mapper

/**
 * @author Do
 * *
 * @package com.eumji.zblog.mapper
 * *
 * @name PagerMapper
 * *
 * @date 2017/4/13
 * *
 * @time 9:07
 */
@Mapper
interface PagerMapper {
    /**
     * 找到目标分类下的分页条件
     * @param categoryId
     * *
     * @return
     */
    fun loadCategoryPager(categoryId: Int): Int

    /**
     * 通过tagId获取文章总数量
     * @param tagId
     * *
     * @return
     */
    fun loadTagPager(tagId: Int): Int
}
