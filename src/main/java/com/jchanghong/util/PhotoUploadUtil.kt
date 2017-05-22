package com.jchanghong.util

import com.jchanghong.vo.PhotoResult
import com.qiniu.common.QiniuException
import com.qiniu.common.Zone
import com.qiniu.storage.BucketManager
import com.qiniu.storage.Configuration
import com.qiniu.storage.UploadManager
import com.qiniu.util.Auth
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

/**
 * FILE: PhotoUploadUtil.java
 * MOTTO:  不积跬步无以至千里,不积小流无以至千里
 * AUTHOR: EumJi
 * DATE: 2017/4/21
 * TIME: 22:08
 */
@Component
class PhotoUploadUtil {
    //设置好账号的ACCESS_KEY和SECRET_KEY
    @Value("\${qiniu.accessKey}")
    lateinit private var ACCESS_KEY: String
    @Value("\${qiniu.secretKey}")
    lateinit private var SECRET_KEY: String
    //要上传的空间
    @Value("\${qiniu.bucketName}")
    lateinit private var bucketname: String
    @Value("\${qiniu.basePath}")
    lateinit var basePath: String

    //上传到七牛后保存的文件名
    fun getFilePath(fileName: String): String {
        val instance = Calendar.getInstance()
        val year = instance.get(Calendar.YEAR)
        val month = instance.get(Calendar.MONTH) + 1
        val day = instance.get(Calendar.DATE)
        return year.toString() + "/" + month + "/" + day + "/" + fileName
    }

    val auth: Auth
        get() = Auth.create(ACCESS_KEY, SECRET_KEY)

    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    val upToken: String
        get() {
            val auth = Auth.create(ACCESS_KEY, SECRET_KEY)
            return auth.uploadToken(bucketname)
        }

    /**
     * 长传图片
     * @param realName 图片路径名
     * *
     * @param filename 图片名
     * *
     * @return
     */
    fun uploadPhoto(realName: String, filename: String): PhotoResult {
        val result = PhotoResult(0, "", "")
        try {
            val cfg = Configuration(Zone.zone2())
            val response = UploadManager(cfg).put(realName, getFilePath(filename), upToken)
            if (response.isOK) {
                result.success = 1
                result.url = basePath + getFilePath(filename)
                return result
            }
        } catch (e: QiniuException) {
            result.success = 0
            result.message = e.message ?: "null"
            return result
        }

        return result
    }

    /**
     * 删除图片
     * @param fileNames
     * *
     * @return
     */
    fun deletePhoto(fileNames: Array<String>): Int {
        var result = 0
        val cfg = Configuration(Zone.zone2())
        val bucketManager = BucketManager(auth, cfg)
        for (filename in fileNames) {
            try {
                bucketManager.delete(bucketname, filename)
                result++
            } catch (e: QiniuException) {
                e.printStackTrace()
                return 0
            }

        }
        return result
    }

}
