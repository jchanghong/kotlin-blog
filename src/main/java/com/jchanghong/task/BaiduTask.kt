package com.jchanghong.task

import com.jchanghong.service.ArticleService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.io.IOException
import java.io.PrintWriter
import java.net.HttpURLConnection
import java.net.URL

/**
 * FILE: BaiduTask.java
 * MOTTO:  不积跬步无以至千里,不积小流无以至千里
 * AUTHOR: EumJi
 * DATE: 2017/4/28
 * TIME: 20:23
 */
@Component
class BaiduTask {
    private val logger = LoggerFactory.getLogger(this.javaClass)
    @Autowired
    lateinit private var articleService: ArticleService

    @Throws(IOException::class)
    private fun initConnect(): HttpURLConnection {
        val conn = URL(POST_URL).openConnection() as HttpURLConnection
        conn.setRequestProperty("Host", "data.zz.baidu.com")
        conn.setRequestProperty("User-Agent", "curl/7.12.1")
        conn.setRequestProperty("Content-Length", "83")
        conn.setRequestProperty("Content-Type", "text/plain")
        conn.doInput = true
        conn.doOutput = true
        return conn
    }

    @Scheduled(fixedDelay = 2000000)
    @Throws(IOException::class)
    fun postArticleUrl() {
        val ids = articleService.articleId
        writerUrl(initConnect(), *ids)

    }

    /**
     * 重构推送文章的write方法
     * @param conn
     * *
     * @param ids
     * *
     * @throws IOException
     */
    @Throws(IOException::class)
    private fun writerUrl(conn: HttpURLConnection, vararg ids: String) {
        val sb = StringBuilder()
        for (i in ids.indices) {
            sb.append(BASE_URL + "/article/details/" + ids[i] + "\n")
        }
        logger.info("推送的url为:" + sb.toString())
        conn.connect()
        var out = PrintWriter(conn.outputStream)
        out.print(sb.toString().trim { it <= ' ' })
        out.flush()
        val code = conn.responseCode
        if (code == 200) {
            logger.info("the article url push success")
        }

    }

    /**
     * 新增添加文章推送功能
     * @param articleId 文章id
     */
    @Throws(IOException::class)
    fun pushOneArticle(articleId: String) {
        writerUrl(initConnect(), articleId)

    }

    companion object {
        val POST_URL = "http://data.zz.baidu.com/urls?site=www.eumji025.com&token=hHzO6TjfJBf4KA53"
        val BASE_URL = "http://www.eumji025.com"
    }
}
