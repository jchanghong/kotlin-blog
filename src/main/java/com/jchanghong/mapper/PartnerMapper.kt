package com.jchanghong.mapper

import com.jchanghong.vo.Pager
import com.jchanghong.vo.Partner
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

/**
 * Created by GeneratorFx on 2017-04-10.
 */
@Mapper
interface PartnerMapper {


    fun findAll(): List<Partner>


    fun savePartner(partner: Partner)

    /**
     * 分页查询好友列表
     * @param pager 分页条件
     * *
     * @param param
     * *
     * @return
     */
    fun loadPartner(@Param("pager") pager: Pager<*>, @Param("param") param: String): List<Partner>

    fun getPartnerById(id: Int): Partner?

    fun deletePartner(id: Int)

    fun updatePartner(partner: Partner)

    fun initPage(pager: Pager<*>): Int
}
