package com.jchanghong.service.impl

import com.jchanghong.mapper.ArticleMapper
import com.jchanghong.service.ArticleService
import com.jchanghong.task.BaiduTask
import com.jchanghong.vo.Article
import com.jchanghong.vo.ArticleCustom
import com.jchanghong.vo.Pager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.io.IOException
import java.util.*
import javax.annotation.Resource

/**
 * Created by GeneratorFx on 2017-04-11.
 */
@Service
@Transactional(rollbackFor = arrayOf(Exception::class))
class ArticleServiceImpl : ArticleService {

    @Resource
    lateinit private var articleMapper: ArticleMapper

    @Resource
    lateinit private var baiduTask: BaiduTask

    override fun articleList(pager: Pager<*>): List<ArticleCustom> {

        return articleMapper.getArticleList(pager)
    }

    override fun InitPager(): Pager<Article> {
        val pager = Pager<Article>()
        val count = articleMapper!!.articleCount
        pager.totalCount = count
        return pager
    }

    override val articleCount: Int
        get() = articleMapper.articleCount

    override fun InitPager(pager: Pager<*>) {
        val count = articleMapper.initPage(pager)
        pager.totalCount = count
    }

    override fun loadArticle(param: Map<String, Any?>): List<Article> {
        return articleMapper.loadArticle(param)
    }

    override fun updateStatue(id: Int, status: Int) {
        articleMapper.updateStatue(id, status)
    }

    @Throws(IOException::class)
    override fun saveArticle(article: Article, tags: IntArray) {
        var id = randomId
        for (i in 0..49) {
            val count = articleMapper.checkExist(id)
            if (count == 0)
                break
            else
                id = randomId
        }
        article.id = id
        article.createTime = Date()
        article.status = 1
        articleMapper.saveArticle(article)
        articleMapper.saveArticleTag(id, tags)
        baiduTask.pushOneArticle(id.toString())
    }

    override fun getArticleById(id: Int): Article? {
        return articleMapper.getArticleById(id)
    }

    override fun updateArticle(article: Article, tags: IntArray) {
        article.updateTime = Date()
        articleMapper.updateArticle(article)
        articleMapper.deleteArticleTag(article.id)
        articleMapper.saveArticleTag(article.id, tags)
    }

    override fun deleteArticle(id: Int) {
        articleMapper.deleteArticle(id)
    }

    override fun getArticleCustomById(articleId: Int): ArticleCustom? {
        return articleMapper.getArticleCustomById(articleId)
    }

    override fun getLastArticle(articleId: Int): Article? {
        return articleMapper.getLastArticle(articleId)
    }

    override fun getNextArticle(articleId: Int): Article? {
        return articleMapper.getNextArticle(articleId)
    }

    override fun addArticleCount(articleId: Int) {
        articleMapper.addArticleCount(articleId)
    }

    override fun popularArticle(): List<ArticleCustom> {
        return articleMapper.popularArticle()
    }

    override val articleId: Array<String>
        get() = articleMapper.articleId

    override fun getArticleListByKeywords(keyword: String): List<Article> {
        return articleMapper.getArticleListByKeywords(keyword)
    }

    override fun articleArchiveList(): List<Map<*, *>> {
        return articleMapper.articleArchiveList()
    }

    private val randomId: Int
        get() {
            val month = Calendar.MONTH
            val dayOfMonth = Calendar.DAY_OF_MONTH
            val random = Random().nextInt(8999) + 1000
            val append = StringBuilder().append(month).append(dayOfMonth).append(random)

            return Integer.valueOf(append.toString())
        }
}
