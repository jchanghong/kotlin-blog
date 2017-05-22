package com.jchanghong.controller.admin

import com.jchanghong.service.ArticleService
import com.jchanghong.service.CategoryService
import com.jchanghong.service.TagService
import com.jchanghong.util.PhotoUploadUtil
import com.jchanghong.util.ResultInfo
import com.jchanghong.util.ResultInfoFactory
import com.jchanghong.vo.Article
import com.jchanghong.vo.Pager
import com.jchanghong.vo.PhotoResult
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.IOException
import java.net.URLDecoder
import java.util.*
import javax.annotation.Resource

/**
 * 后台管理 文章controller
 * FILE: AdminArticleController.java
 * MOTTO:  不积跬步无以至千里,不积小流无以至千里
 * AUTHOR: EumJi
 * DATE: 2017/4/15
 * TIME: 22:00
 */
@Controller
@RequestMapping("/admin/article")
class AdminArticleController {
    private var log = LoggerFactory.getLogger(AdminArticleController::class.java)
    //文章service
    @Resource
    lateinit private var articleService: ArticleService

    @Resource
    lateinit private var photoUploadUtil: PhotoUploadUtil

    //标签service
    @Resource
    lateinit private var tagService: TagService

    //分类service
    @Resource
    lateinit private var categoryService: CategoryService

    /**
     * 初始化文章分页信息
     * @param pager
     * *
     * @return
     */
    @RequestMapping("/initPage")
    @ResponseBody
    fun initPage(pager: Pager<*>): Pager<*> {
        articleService.InitPager(pager)
        return pager
    }

    /**
     * 跳转到添加页面
     * @return
     */
    @RequestMapping("/addPage")
    fun addPage(): String {
        return "admin/article/articleAdd"
    }

    /**
     * 初始化文章列表
     * @param pager 分页对象
     * *
     * @param categoryId 搜索条件 分类id
     * *
     * @param tagIds 搜索条件 tag集合
     * *
     * @param title 搜索条件 文章标题
     * *
     * @param model
     * *
     * @return
     */
    @RequestMapping("/load")
    fun loadArticle(pager: Pager<*>, categoryId: Int?, tagIds: String?, title: String?, model: Model): String {
        /**
         * 设置start位置
         */
        pager.start = pager.start
        //封装查询条件
        var param = HashMap<String, Any?>()
        if (tagIds != null && "" != tagIds) {
            param.put("tags", tagIds.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
        } else {
            param.put("tags", null)
        }
        param.put("categoryId", categoryId ?: 0)
        param.put("title", title)
        param.put("pager", pager)
        //获取文章列表
        var articleList = articleService.loadArticle(param)
        model.addAttribute("articleList", articleList)
        return "admin/article/articleTable"
    }

    /**
     * 更新文章可用状态
     * @param id
     * *
     * @param status
     * *
     * @return
     */
    @RequestMapping("/updateStatue")
    @ResponseBody
    fun updateStatue(id: Int?, status: Int): ResultInfo {
        try {

            articleService.updateStatue(id ?: 0, status)
        } catch (e: Exception) {
            log.error(e.toString())
            return ResultInfoFactory.getErrorResultInfo("更新状态失败,请稍后再尝试")
        }

        return ResultInfoFactory.successResultInfo
    }

    /**
     * 获取条件,所有tag和category
     * @param model
     * *
     * @return
     */
    @RequestMapping("/term")
    fun articleTerm(model: Model): String {
        var tagList = tagService.tagList
        var categoryList = categoryService.categoryList
        model.addAttribute("tagList", tagList)
        model.addAttribute("categoryList", categoryList)
        return "admin/article/articleInfo"
    }

    /**
     * 保存文章
     * @param article
     * *
     * @param tags
     * *
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    fun SaveArticle(article: Article, tags: IntArray): ResultInfo {
        try {
            //解码文章内容防止出现部分特殊字符串被转义
            article.description = URLDecoder.decode(article.description, "UTF-8")
            article.title = URLDecoder.decode(article.title, "UTF-8")
            article.content = URLDecoder.decode(article.content, "UTF-8")
            articleService.saveArticle(article, tags)
        } catch (e: Exception) {
            log.error(e.toString())
            return ResultInfoFactory.getErrorResultInfo("添加失败,请稍后再尝试")
        }

        return ResultInfoFactory.successResultInfo

    }

    /**
     * 跳转到编辑页面
     * @param id
     * *
     * @param model
     * *
     * @return
     */
    @RequestMapping("/editJump")
    fun updatePage(id: Int?, model: Model): String {
        var article = articleService.getArticleById(id ?: 0)
        model.addAttribute("article", article)
        return "admin/article/articleEdit"
    }

    /**
     * 获取更新文章信息
     * @param articleId 文章标题 用于获取文章信息
     * *
     * @param model
     * *
     * @return
     */
    @RequestMapping("/updateInfo")
    fun updateInfo(articleId: Int?, model: Model): String {
        var article = articleService.getArticleById(articleId ?: 0)
        var categoryList = categoryService.categoryList
        var tagList = tagService.tagList
        model.addAttribute("article", article)
        model.addAttribute("categoryList", categoryList)
        model.addAttribute("tagList", tagList)
        return "admin/article/articleEditInfo"
    }

    /**
     * 更新文章
     * @param article
     * *
     * @param tags
     * *
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    fun updateArticle(article: Article, tags: IntArray): ResultInfo {
        try {
            //解码文章内容防止出现部分特殊字符串被转义
            article.description = URLDecoder.decode(article.description, "UTF-8")
            article.title = URLDecoder.decode(article.title, "UTF-8")
            article.content = URLDecoder.decode(article.content, "UTF-8")
            articleService.updateArticle(article, tags)
        } catch (e: Exception) {
            log.error(e.toString())
            ResultInfoFactory.getErrorResultInfo("修改失败,请稍后再试!")
        }

        return ResultInfoFactory.successResultInfo
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    fun deleteArticle(@PathVariable id: Int?): ResultInfo {
        try {

            articleService.deleteArticle(id ?: 0)
        } catch (e: Exception) {
            log.error(e.toString())
            return ResultInfoFactory.getErrorResultInfo("删除失败!")
        }

        return ResultInfoFactory.successResultInfo
    }

    @RequestMapping("/imageUpload")
    @ResponseBody
    fun imageUpload(@RequestParam(value = "editormd-image-file", required = true) file: MultipartFile) {
        //设置filename
        // String filename = new Random().nextInt(10000)+file.getOriginalFilename();
        try {
            var files = File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + file.originalFilename)
            file.transferTo(files)
            photoUploadUtil.uploadPhoto(files.absolutePath, file.originalFilename)
        } catch (e: IOException) {
            e.printStackTrace()
            PhotoResult(0, "", "")
        }
    }
}