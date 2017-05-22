package com.jchanghong.service


import com.jchanghong.vo.ArticleCustom
import com.jchanghong.vo.Category
import com.jchanghong.vo.CategoryCustom
import com.jchanghong.vo.Pager

/**
 * Created by GeneratorFx on 2017-04-11.
 */
interface CategoryService {


    fun loadArticleByCategory(pager: Pager<*>, categoryId: Int): List<ArticleCustom>

    /**
     * 初始化分类信息
     * @return
     */
    fun initCategoryList(): List<CategoryCustom>

    fun getCategoryById(id: Int): Category?

    fun loadCategory(pager: Pager<*>, categoryName: String): List<Category>

    fun checkExist(category: Category): Boolean

    fun saveCategory(category: Category)

    fun updateCategory(category: Category)

    fun initPage(pager: Pager<*>)

    val categoryList: List<Category>

    fun ArticleCatePage(pager: Pager<*>, categoryId: Int)

    fun loadArticleByArchive(createTime: String, pager: Pager<*>): List<ArticleCustom>

    fun getArticleCountByCategoryId(categoryId: Int): Int

    fun deleteCategoryById(categoryId: Int): Boolean
}
