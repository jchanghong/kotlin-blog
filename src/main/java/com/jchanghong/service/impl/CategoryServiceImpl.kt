package com.jchanghong.service.impl

import com.jchanghong.mapper.ArticleMapper
import com.jchanghong.mapper.CategoryMapper
import com.jchanghong.service.CategoryService
import com.jchanghong.vo.ArticleCustom
import com.jchanghong.vo.Category
import com.jchanghong.vo.CategoryCustom
import com.jchanghong.vo.Pager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import javax.annotation.Resource

/**
 * Created by GeneratorFx on 2017-04-11.
 */
@Service
@Transactional
class CategoryServiceImpl : CategoryService {
    @Resource
    lateinit private var categoryMapper: CategoryMapper

    @Resource
    lateinit private var articleMapper: ArticleMapper


    override fun loadArticleByCategory(pager: Pager<*>, categoryId: Int): List<ArticleCustom> {
        pager.start
        return articleMapper.loadArticleByCategory(pager, categoryId)
    }

    override fun initCategoryList(): List<CategoryCustom> {
        return categoryMapper.initCategoryList()
    }

    override fun getCategoryById(id: Int): Category? {
        return categoryMapper.getCategoryById(id)
    }

    override fun loadCategory(pager: Pager<*>, categoryName: String): List<Category> {
        pager.start = pager.start
        return categoryMapper.loadCategory(pager, categoryName)
    }

    override fun checkExist(category: Category): Boolean {
        val count = categoryMapper.checkExist(category)
        if (count > 0) {
            return true
        }
        return false
    }

    override fun saveCategory(category: Category) {
        categoryMapper.saveCategory(category)
    }

    override fun updateCategory(category: Category) {
        categoryMapper.updateCategory(category)
    }

    override fun initPage(pager: Pager<*>) {
        val count = categoryMapper.initPage(pager)
        pager.totalCount = count
    }

    override val categoryList: List<Category>
        get() = categoryMapper.categoryList

    override fun ArticleCatePage(pager: Pager<*>, categoryId: Int) {
        val count = categoryMapper.ArticleCatePage(categoryId)
        pager.totalCount = count
    }

    override fun loadArticleByArchive(createTime: String, pager: Pager<*>): List<ArticleCustom> {
        pager.start
        return articleMapper.loadArticleByArchive(pager, createTime)
    }

    override fun getArticleCountByCategoryId(categoryId: Int): Int {
        return categoryMapper.ArticleCatePage(categoryId)
    }

    override fun deleteCategoryById(categoryId: Int): Boolean {
        categoryMapper.deleteCategoryById(categoryId)
        articleMapper.updateCategoryId(categoryId)
        return true
    }

}
