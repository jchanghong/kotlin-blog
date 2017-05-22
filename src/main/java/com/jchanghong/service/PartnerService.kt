package com.jchanghong.service

import com.jchanghong.vo.Pager
import com.jchanghong.vo.Partner

/**
 * Created by GeneratorFx on 2017-04-10.
 */
interface PartnerService {

    fun findAll(): List<Partner>

    fun savePartner(partner: Partner)

    /**
     * 分页查询好友列表
     * @param pager
     * *
     * @param param
     * *
     * @return
     */
    fun loadPartner(pager: Pager<*>, param: String): List<Partner>

    fun getPartnerById(id: Int): Partner?

    fun deletePartner(id: Int)

    fun updatePartner(partner: Partner)

    fun initPage(pager: Pager<*>)
}
