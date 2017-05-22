package com.jchanghong.mapper

import com.jchanghong.vo.Pager
import com.jchanghong.vo.Tag
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

/**
 * @author Do
 * *
 * @package com.eumji.zblog.mapper
 * *
 * @name TagMapper
 * *
 * @date 2017/4/13
 * *
 * @time 18:55
 */
@Mapper
interface TagMapper {

    val tagCount: Int

    fun getTagById(id: Int): Tag?

    fun loadTagList(@Param("pager") pager: Pager<*>, @Param("tagName") tagName: String): List<Tag>

    fun saveTag(tag: Tag)

    fun checkExist(tag: Tag): Int

    fun updateTag(tag: Tag)

    fun initPage(pager: Pager<*>): Int

    val tagList: List<Tag>

    /**
     * 加载此tag下的所有文章
     * @return
     * *
     * @param tagId
     */
    fun articleTagPage(tagId: Int): Int
}
