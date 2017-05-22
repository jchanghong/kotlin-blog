package com.jchanghong.service

import com.jchanghong.vo.Article
import com.jchanghong.vo.ArticleCustom
import com.jchanghong.vo.Pager

import java.io.IOException

/**
 * Created by GeneratorFx on 2017-04-11.
 */
interface ArticleService {


    fun articleList(pager: Pager<*>): List<ArticleCustom>

    /**
     * 获取分页信息
     * @return
     */
    fun InitPager(): Pager<Article>

    val articleCount: Int

    /**
     * 初始化后台管理的article分页信息
     * @param pager 分页对象
     */
    fun InitPager(pager: Pager<*>)

    fun loadArticle(param: Map<String, Any?>): List<Article>

    fun updateStatue(id: Int, status: Int)

    @Throws(IOException::class)
    fun saveArticle(article: Article, tags: IntArray)

    fun getArticleById(id: Int): Article?

    fun updateArticle(article: Article, tags: IntArray)

    fun deleteArticle(id: Int)

    fun getArticleCustomById(articleId: Int): ArticleCustom?

    /**
     * 获取上一篇文章信息
     * @param articleId
     * *
     * @return
     */
    fun getLastArticle(articleId: Int): Article?

    /**
     * 获取下一篇文章信息
     * @param articleId
     * *
     * @return
     */
    fun getNextArticle(articleId: Int): Article?

    /**
     * 增加文章阅读数量
     * @param articleId
     */
    fun addArticleCount(articleId: Int)

    fun popularArticle(): List<ArticleCustom>

    val articleId: Array<String>

    fun getArticleListByKeywords(keyword: String): List<Article>

    fun articleArchiveList(): List<Map<*, *>>
}
