package com.jchanghong.controller.admin

import com.jchanghong.service.PartnerService
import com.jchanghong.util.ResultInfo
import com.jchanghong.util.ResultInfoFactory
import com.jchanghong.vo.Pager
import com.jchanghong.vo.Partner
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URLDecoder
import javax.annotation.Resource

/**
 * FILE: com.eumji.zblog.controller.admin.PartnerController.java
 * MOTTO:  不积跬步无以至千里,不积小流无以至千里
 * AUTHOR: EumJi
 * DATE: 2017/4/14
 * TIME: 22:28
 */
@RestController
@RequestMapping("/admin/partner")
class AdminPartnerController {
    @Resource
    lateinit private var partnerService: PartnerService

    @RequestMapping("/initPage")
    fun initPage(pager: Pager<*>): Pager<*> {
        partnerService.initPage(pager)
        return pager
    }

    @RequestMapping("/save")
    fun savePartner(partner: Partner): ResultInfo {
        try {
            partner.siteName = URLDecoder.decode(partner.siteName, "utf-8")
            partner.siteDesc = URLDecoder.decode(partner.siteDesc, "utf-8")
            partner.siteUrl = URLDecoder.decode(partner.siteUrl, "utf-8")
            partnerService.savePartner(partner)
        } catch (e: Exception) {
            return ResultInfoFactory.errorResultInfo
        }

        return ResultInfoFactory.successResultInfo

    }

    @RequestMapping("/update")
    fun updatePartner(partner: Partner): ResultInfo {
        try {
            partner.siteName = URLDecoder.decode(partner.siteName, "utf-8")
            partner.siteDesc = URLDecoder.decode(partner.siteDesc, "utf-8")
            partner.siteUrl = URLDecoder.decode(partner.siteUrl, "utf-8")
            if (false) {
                partner.sort = 0
            }
            partnerService.updatePartner(partner)
        } catch (e: Exception) {
            return ResultInfoFactory.errorResultInfo
        }

        return ResultInfoFactory.successResultInfo

    }

    @RequestMapping("/delete/{id}")
    fun deletePartner(@PathVariable id: Int?): ResultInfo {
        try {
            partnerService.deletePartner(id ?: 0)
        } catch (e: Exception) {
            return ResultInfoFactory.errorResultInfo
        }

        return ResultInfoFactory.successResultInfo
    }
}
