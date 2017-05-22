package com.jchanghong.controller.admin

import com.jchanghong.service.CategoryService
import com.jchanghong.util.ResultInfo
import com.jchanghong.util.ResultInfoFactory
import com.jchanghong.vo.Category
import com.jchanghong.vo.Pager
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import javax.annotation.Resource

/**
 * 后台管理的分类controller
 * FILE: AdminCategoryController.java
 * MOTTO:  不积跬步无以至千里,不积小流无以至千里
 * AUTHOR: EumJi
 * DATE: 2017/4/15
 * TIME: 14:43
 */
@Controller
@RequestMapping("/admin/category")
class AdminCategoryController {

    @Resource
    lateinit private var categoryService: CategoryService

    /**
     * 跳转到分类列表页面
     * @return 分类列表页面
     */
    @RequestMapping("/list")
    fun categoryPage(): String {
        return "admin/category/categoryList"
    }

    /**
     * 初始化分页信息 获取totalcount
     * @param pager 分页对象
     * *
     * @return
     */
    @RequestMapping("/initPage")
    @ResponseBody
    fun initPage(pager: Pager<*>): Pager<*> {
        categoryService.initPage(pager)
        return pager
    }

    /**
     * 跳转到添加页面
     * @return
     */
    @RequestMapping("/addJump")
    fun addPage(): String {
        return "admin/category/categoryAdd"
    }

    /**
     * 跳转修改页面
     * @param categoryId 分类id
     * *
     * @param model
     * *
     * @return
     */
    @RequestMapping("/editJump/{categoryId}")
    fun editPage(@PathVariable categoryId: Int?, model: Model): String {
        val category = categoryService.getCategoryById(categoryId ?: 0)
        model.addAttribute("category", category)
        return "admin/category/categoryEdit"
    }

    /**
     * 加载分类信息列表
     * @param pager 分页对象
     * *
     * @param categoryName  搜索条件
     * *
     * @param model
     * *
     * @return
     */
    @RequestMapping("/load")
    fun loadCategory(pager: Pager<*>, categoryName: String?, model: Model): String {
        val categoryList = categoryService.loadCategory(pager, categoryName?:"")
        model.addAttribute("categoryList", categoryList)
        return "admin/category/categoryTable"
    }


    /**
     * 添加分类信息
     * @param category 分类信息对象
     * *
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    fun saveCateogry(category: Category): ResultInfo {
        try {
            //解码
            category.categoryName = URLDecoder.decode(category.categoryName, "UTF-8")
            category.aliasName = URLDecoder.decode(category.aliasName, "UTF-8")
            if (false) {
                category.sort = 0
            }
            //检查是否已存在
            if (categoryService.checkExist(category)) {
                return ResultInfoFactory.getErrorResultInfo("分类名称或别名已存在")
            } else {
                categoryService.saveCategory(category)
            }
        } catch (e: UnsupportedEncodingException) {
            return ResultInfoFactory.getErrorResultInfo("文本解析错误,稍后再尝试")
        } catch (e: Exception) {
            return ResultInfoFactory.getErrorResultInfo("添加失败,请通知管理员")
        }

        return ResultInfoFactory.successResultInfo
    }

    /**
     * 更新分类信息
     * @param category
     * *
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    fun updateCategory(category: Category): ResultInfo {

        try {
            //解码
            category.categoryName = URLDecoder.decode(category.categoryName, "UTF-8")
            category.aliasName = URLDecoder.decode(category.aliasName, "UTF-8")
            if (false) {
                category.sort = 0
            }
            //检查是否存在
            if (categoryService.checkExist(category)) {
                return ResultInfoFactory.getErrorResultInfo("分类的名称或别名已存在")
            } else {
                categoryService.updateCategory(category)
            }
        } catch (e: UnsupportedEncodingException) {
            ResultInfoFactory.getErrorResultInfo("字符串解析错误,请稍后在尝试")
        }

        return ResultInfoFactory.successResultInfo
    }

    /**
     * 删除之前查询是否存在文章
     * @param categoryId
     * *
     * @return
     */
    @RequestMapping("/query/{categoryId}")
    @ResponseBody
    fun checkExist(@PathVariable categoryId: Int?): ResultInfo {
        val count = categoryService.getArticleCountByCategoryId(categoryId ?: 0)
        if (count > 0) {
            return ResultInfoFactory.getErrorResultInfo("当前分类已存在文章")
        }
        return ResultInfoFactory.successResultInfo
    }

    @RequestMapping("/delete/{categoryId}")
    @ResponseBody
    fun deleteCategory(@PathVariable categoryId: Int?): ResultInfo {
        val flag = categoryService.deleteCategoryById(categoryId ?: 0)
        if (flag) {
            return ResultInfoFactory.getErrorResultInfo("删除分类成功！！！")
        }
        return ResultInfoFactory.errorResultInfo
    }

}
