package com.jchanghong.mapper


import com.jchanghong.vo.Category
import com.jchanghong.vo.CategoryCustom
import com.jchanghong.vo.Pager
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

/**
 * Created by GeneratorFx on 2017-04-11.
 */
@Mapper
interface CategoryMapper {

    /**
     * 初始化分类信息

     * @return
     */
    fun initCategoryList(): List<CategoryCustom>

    fun getCategoryById(id: Int): Category?

    fun loadCategory(@Param("pager") pager: Pager<*>, @Param("categoryName") categoryName: String): List<Category>

    fun checkExist(category: Category): Int

    fun saveCategory(category: Category)

    fun updateCategory(category: Category)

    fun initPage(pager: Pager<*>): Int

    val categoryList: List<Category>

    /**
     * 获取当前id的文章数量

     * @param categoryId
     * *
     * @return
     */
    fun ArticleCatePage(categoryId: Int): Int

    fun deleteCategoryById(categoryId: Int)
}
