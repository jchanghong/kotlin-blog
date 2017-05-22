package com.jchanghong.controller

import com.jchanghong.service.ArticleService
import com.jchanghong.service.CategoryService
import com.jchanghong.service.PartnerService
import com.jchanghong.service.TagService
import com.jchanghong.util.ResultInfo
import com.jchanghong.vo.Article
import com.jchanghong.vo.Pager
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

import javax.annotation.Resource

/**
 * Created by GeneratorFx on 2017-04-11.
 */
@Controller
@RequestMapping("/article")
class ArticleController {

    @Resource
    lateinit private var articleService: ArticleService

    @Resource
    lateinit private var partnerService: PartnerService

    @Resource
    lateinit private var categoryService: CategoryService

    @Resource
    lateinit private var tagService: TagService

    @RequestMapping("/list/{id}")
    @ResponseBody
    fun articleList(pager: Pager<*>): ResultInfo? {

        return null
    }

    @RequestMapping("/load")
    fun loadArticle(pager: Pager<Article>, model: Model): String {
        var articleList = articleService.articleList(pager)
        println(articleList)
        model.addAttribute("articleList", articleList)
        return "blog/part/articleSummary"
    }

    /**
     * 加载文章
     * 包括总标签数
     * 总文章数量
     * 分类及每个分类文章数量
     * 友链集合

     * @return
     */
    @RequestMapping("/details/{articleId}")
    fun loadArticle(@PathVariable articleId: Int?, model: Model): String {
        var partnerList = partnerService.findAll()
        var categoryList = categoryService.initCategoryList()
        var lastArticle = articleService.getLastArticle(articleId ?: 0)
        var nextArticle = articleService.getNextArticle(articleId ?: 0)
        articleService.addArticleCount(articleId ?: 0)
        var articleCount = articleService.articleCount
        var tagCount = tagService.tagCount
        var articleCustom = articleService.getArticleCustomById(articleId ?: 0)
        model.addAttribute("lastArticle", lastArticle)
        model.addAttribute("nextArticle", nextArticle)
        model.addAttribute("article", articleCustom)
        model.addAttribute("categoryCount", categoryList.size)
        model.addAttribute("articleCount", articleCount)
        model.addAttribute("tagCount", tagCount)
        model.addAttribute("categoryList", categoryList)
        model.addAttribute("partnerList", partnerList)
        return "article"
    }

    @RequestMapping("/content/search")
    fun search(keyword: String?, model: Model): String {
        var articleList = articleService.getArticleListByKeywords(keyword?:"")
        model.addAttribute("articleList", articleList)
        return "blog/part/search-info"
    }


}
