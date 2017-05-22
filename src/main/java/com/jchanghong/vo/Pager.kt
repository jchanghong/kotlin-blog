package com.jchanghong.vo

import java.io.Serializable
import java.util.*

/**
 * @author Do
 * *
 * @package com.eumji.zblog.vo
 * *
 * @name Pager
 * *
 * @date 2017/4/11
 * *
 * @time 12:32
 */
class Pager<T> : Serializable {

    /**

     * 页数

     */
    var page: Int = 0

    /**

     * 每页显示数

     */
    var limit = PAGE_SIZE

    /**

     * 总页数

     */
    var totalPageNum: Int = 0

    /**

     * 记录总数

     */
    // 计算最大页数
    var totalCount: Int = 0
        set(totalCount) {
            field = totalCount
            if (totalCount > 0) {
                if (page <= 0) {
                    page = PAGE_NUM
                }

                val pageCount = totalCount / PAGE_SIZE
                if (totalCount % PAGE_SIZE == 0) {
                    totalPageNum = pageCount
                } else {
                    totalPageNum = pageCount + 1
                }
            } else {
                totalPageNum = 0
            }

            if (page > totalPageNum) {
                page = totalPageNum
            }
        }

    /**

     * 分页信息

     */
    var rows: List<T> = ArrayList()

    /**

     * 分页计算起始值

     */
    // 分页开始值 关键
    var start: Int = 0
        get() {

            if (page == 0) {
                this.start = 0
            } else {
                this.start = (page - 1) * limit
            }
            return field
        }

    /**
     * 分页计算结束值  暂时没用
     */
    var endIndex: Int = 0

    fun setPageNum(pageNum: Int) {
        var pageNum = pageNum
        if (pageNum <= 0) {
            pageNum = PAGE_NUM
        }
        if (pageNum > totalPageNum) {
            pageNum = totalPageNum
        }
        // 分页开始值 关键

        if (pageNum == 0) {
            this.start = 0
        } else {
            this.start = (pageNum - 1) * limit
        }
        this.page = pageNum
    }

    fun setPageSize(pageSize: Int) {
        var pageSize = pageSize
        if (pageSize <= 0) {
            pageSize = PAGE_SIZE
        }
        // 计算最大页数

        val pageCount = this.totalCount / pageSize
        if (this.totalCount % pageSize == 0) {
            totalPageNum = pageCount
        } else {
            totalPageNum = pageCount + 1
        }
        this.limit = pageSize
    }

    companion object {

        /**

         * 默认每页显示数

         */
        val PAGE_SIZE = 10

        /**

         * 默认页数

         */
        val PAGE_NUM = 1
    }


}