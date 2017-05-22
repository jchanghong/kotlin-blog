package com.jchanghong.service.impl

import com.jchanghong.mapper.PartnerMapper
import com.jchanghong.service.PartnerService
import com.jchanghong.vo.Pager
import com.jchanghong.vo.Partner
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import javax.annotation.Resource

/**
 * Created by GeneratorFx on 2017-04-10.
 */
@Service
@Transactional
class PartnerServiceImpl : PartnerService {

    @Resource
    lateinit private var partnerMapper: PartnerMapper


    override fun findAll(): List<Partner> {
        return partnerMapper.findAll()
    }

    override fun savePartner(partner: Partner) {
        partnerMapper.savePartner(partner)
    }

    override fun loadPartner(pager: Pager<*>, param: String): List<Partner> {
        pager.start = pager.start
        return partnerMapper.loadPartner(pager, param)
    }

    override fun getPartnerById(id: Int): Partner? {
        return partnerMapper.getPartnerById(id)
    }

    override fun deletePartner(id: Int) {
        partnerMapper.deletePartner(id)
    }

    override fun updatePartner(partner: Partner) {
        partnerMapper.updatePartner(partner)
    }

    override fun initPage(pager: Pager<*>) {
        val count = partnerMapper.initPage(pager)
        pager.totalCount = count
    }

}
