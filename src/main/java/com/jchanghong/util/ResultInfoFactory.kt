package com.jchanghong.util

/**
 * 获取错误信息的工具类
 * FILE: com.eumji.zblog.util.UserInfoUtil.java
 * MOTTO:  不积跬步无以至千里,不积小流无以至千里
 * AUTHOR: EumJi
 * DATE: 2017/4/9
 * TIME: 15:39
 */
object ResultInfoFactory {
    var ERROR_RESULT: ResultInfo = ResultInfo("fail", "操作失败！！！")
    var SUCCESS_RESULT: ResultInfo = ResultInfo("success", "操作成功")

    /**
     * 带错误信息错误信息相应体
     * @param errorInfo
     * *
     * @return
     */
    fun getErrorResultInfo(errorInfo: String): ResultInfo {
        ERROR_RESULT.errorInfo = errorInfo
        return ERROR_RESULT
    }

    /**
     * 不带参数错误信息相应体
     * 默认为错误信息为操作失败
     * @return
     */
    val errorResultInfo: ResultInfo
        get() = getErrorResultInfo("操作失败！！！")

    /**
     * 带参数正确的实体相应题
     * @param errorInfo
     * *
     * @return
     */
    fun getSuccessResultInfo(errorInfo: String): ResultInfo {
        SUCCESS_RESULT.errorInfo = errorInfo
        return SUCCESS_RESULT
    }

    /**
     * 不带参数正确的信息相应体
     * 默认为错误信息为操作成功
     * @return
     */
    val successResultInfo: ResultInfo
        get() = getSuccessResultInfo("操作成功！！！")


}
