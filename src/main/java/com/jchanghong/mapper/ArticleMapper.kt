package com.jchanghong.mapper


import com.jchanghong.vo.Article
import com.jchanghong.vo.ArticleCustom
import com.jchanghong.vo.Pager
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

/**
 * Created by GeneratorFx on 2017-04-11.
 */
@Mapper
interface ArticleMapper {

    val articleCount: Int

    fun getArticleList(pager: Pager<*>): List<ArticleCustom>

    fun loadArticleByCategory(@Param("pager") pager: Pager<*>, @Param("categoryId") categoryId: Int): List<ArticleCustom>

    /**
     * 通过tagid分页获取当前tag下的文章列表
     * @param pager
     * *
     * @param tagId
     * *
     * @return
     */
    fun loadArticleByTag(@Param("pager") pager: Pager<*>, @Param("tagId") tagId: Int): List<ArticleCustom>


    fun initPage(pager: Pager<*>): Int

    fun loadArticle(param: Map<String, Any?>): List<Article>

    fun updateStatue(@Param("id") id: Int, @Param("status") status: Int)

    fun saveArticle(article: Article)

    fun saveArticleTag(@Param("articleId") articleId: Int, @Param("tags") tags: IntArray)

    fun checkExist(id: Int): Int

    fun getArticleById(id: Int): Article?

    fun updateArticle(article: Article)

    fun deleteArticleTag(articleId: Int)

    fun deleteArticle(id: Int)

    fun getArticleCustomById(id: Int): ArticleCustom?

    /**
     * 获取上一篇文章
     * @param id
     * *
     * @return
     */
    fun getLastArticle(id: Int): Article?

    /**
     * 获取下一篇文章
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

    fun getArticleListByKeywords(@Param("keyword") keyword: String): List<Article>

    fun articleArchiveList(): List<Map<*, *>>

    fun loadArticleByArchive(@Param("pager") pager: Pager<*>, @Param("createTime") createTime: String): List<ArticleCustom>

    fun updateCategoryId(categoryId: Int)
}
